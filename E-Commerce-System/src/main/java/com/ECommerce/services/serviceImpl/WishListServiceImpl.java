package com.ECommerce.services.serviceImpl;

import com.ECommerce.Entities.Product;
import com.ECommerce.Entities.User;
import com.ECommerce.Entities.WishList;
import com.ECommerce.repository.ProductRepository;
import com.ECommerce.repository.UserRepository;
import com.ECommerce.repository.WishListRepository;
import com.ECommerce.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service

public class WishListServiceImpl implements WishListService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public WishList addProductToWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        Optional<WishList> existing = wishListRepository.findByUser(user);
        WishList wishList;
        if(existing.isPresent()){
            wishList = existing.get();
        } else {
            wishList = new WishList();
            wishList.setUser(user);
            wishList.setAddedDate(LocalDate.now());
        }
        wishList.getProducts().add(product);
        return wishListRepository.save(wishList);
    }

    @Override
    public WishList getWishlistByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return wishListRepository.findByUser(user).orElse(new WishList());
    }

    @Override
    public WishList removeProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow();
        WishList wishList = wishListRepository.findByUser(user).orElseThrow();
        wishList.getProducts().removeIf(p -> p.getId().equals(productId));
        return wishListRepository.save(wishList);
    }
}
