package com.ECommerce.services.serviceImpl;

import com.ECommerce.Entities.Cart;
import com.ECommerce.Entities.Role;
import com.ECommerce.Entities.User;
import com.ECommerce.repository.CartRepository;
import com.ECommerce.repository.UserRepository;
import com.ECommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        User saved = userRepository.save(user);

//         creating new cart for every user
        Cart cart = new Cart();
        cart.setUser(saved);
        cart.setQuantity(0);
        cart.setTotalAmount(0);
        Cart cart1 = cartRepository.save(cart);

        saved.setCart(cart1);
        return saved;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found : " + userId));


    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found with email id: "+email));
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found: " + userId));
        userRepository.delete(user);
    }
}
