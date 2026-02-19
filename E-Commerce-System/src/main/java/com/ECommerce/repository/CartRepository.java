package com.ECommerce.repository;

import com.ECommerce.Entities.Cart;
import com.ECommerce.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUser(User user);
}
