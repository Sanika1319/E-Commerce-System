package com.ECommerce.Controller;

import com.ECommerce.Entities.Cart;
import com.ECommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/getCartByUserId/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(cartService.getCartByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/addProducts/{userId}/{productId}")
    public ResponseEntity<Cart>addProductToCart(@PathVariable Long userId,@PathVariable Long productId,@RequestParam int quantity){
        return new ResponseEntity<>(cartService.addProductToCart(userId, productId, quantity),HttpStatus.OK);
    }

    @DeleteMapping("/removeProductFromCart/{userId}/{productId}")
    public ResponseEntity<Cart>removeProductFromCart(@PathVariable Long userId,@PathVariable Long productId){
        return new ResponseEntity<>(cartService.removeProductFromCart(userId, productId),HttpStatus.OK);
    }
    @DeleteMapping("/clearCart/{userId}")
    public ResponseEntity<Cart>clearCart(@PathVariable Long userId){
        return new ResponseEntity<>(cartService.clearCart(userId),HttpStatus.OK);
    }
}
