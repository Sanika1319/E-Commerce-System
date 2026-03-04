package com.ECommerce.services;

import com.ECommerce.Entities.Cart;

public interface CartService {
    Cart getCartByUserId(Long userId);
    Cart addProductToCart(Long userId, Long productId, int quantity);

    Cart removeProductFromCart(Long userId, Long productId);

    Cart clearCart(Long userId);

    void recalculateCart(Cart cart);

}
