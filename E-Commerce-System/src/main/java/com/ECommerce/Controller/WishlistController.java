package com.ECommerce.Controller;

import com.ECommerce.Entities.WishList;
import com.ECommerce.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishList")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class WishlistController {
    @Autowired
    private WishListService wishListService;
    @PostMapping("/add/{userId}/{productId}")
    public WishList addProduct(@PathVariable  Long userId,
                               @PathVariable Long productId){
        return wishListService.addProductToWishlist(userId, productId);
    }
    @GetMapping("/user/{userId}")
    public WishList getWishlist(@PathVariable Long userId){
        return wishListService.getWishlistByUser(userId);
    }
    @DeleteMapping("/remove/{userId}/{productId}")
    public WishList removeProduct(@PathVariable Long userId,
                                  @PathVariable Long productId) {
        return wishListService.removeProduct(userId, productId);
    }
    }
