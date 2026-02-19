package com.ECommerce.Controller;

import com.ECommerce.Entities.User;
import com.ECommerce.request.LoginRequest;
import com.ECommerce.services.UserService;
import com.ECommerce.services.serviceImpl.CustomUserDetailsService;
import com.ECommerce.services.serviceImpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword())
                );

        if(authenticate.isAuthenticated()){
            String jwt = jwtService.generateToken(customUserDetailsService.loadUserByUsername(loginRequest.getEmail()));
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Unauthorized user",HttpStatus.UNAUTHORIZED);
        }
    }
}
