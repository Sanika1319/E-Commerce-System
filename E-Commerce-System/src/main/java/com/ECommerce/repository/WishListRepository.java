package com.ECommerce.repository;

import com.ECommerce.Entities.User;
import com.ECommerce.Entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<WishList> findByUser(User user);

}
