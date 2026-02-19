package com.ECommerce.services.serviceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
@Service

public class JwtService {
    private final static String SECRET = "snfhiuerhdnewpughbjreoifuygbhjkjfeoiufhbjefiobuihjkreiouklnedjoihejkoeyhuku";

    private final static long VALIDATE = TimeUnit.MINUTES.toMillis(30);

    public SecretKey getSecretKey(){
        byte[] decoded = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decoded);
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",userDetails.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(VALIDATE)))
                .signWith(getSecretKey())

                .compact();

    }
    public String extractEmail(String jwt){
        return extractClaims(jwt, Claims::getSubject);
    }
    public Date extractExpiration(String jwt){
        return extractClaims(jwt, Claims::getExpiration);
    }
    public <T> T extractClaims(String jwt, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String jwt){
        return extractExpiration(jwt).before(Date.from(Instant.now()));
    }
    public boolean isTokenValid(String jwt, UserDetails userDetails){
        String email = extractEmail(jwt);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(jwt);

    }


    public Claims extractAllClaims(String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

}
