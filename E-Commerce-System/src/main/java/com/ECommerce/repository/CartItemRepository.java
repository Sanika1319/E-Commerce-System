package com.ECommerce.repository;

import com.ECommerce.Entities.Cart;
import com.ECommerce.Entities.CartItem;
import com.ECommerce.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // 1️⃣ Find product inside a specific cart
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    // 2️⃣ Delete all items of a cart (used in clearCart)
    void deleteByCart(Cart cart);

    // 3️⃣ Get all items by cart
    List<CartItem> findByCart(Cart cart);
}
