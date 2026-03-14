package com.ECommerce.services;

import com.ECommerce.Entities.WishList;

public interface WishListService {
    WishList addProductToWishlist(Long userId, Long productId);
    WishList getWishlistByUser(Long userId);
    WishList removeProduct(Long userId, Long productId);
}
