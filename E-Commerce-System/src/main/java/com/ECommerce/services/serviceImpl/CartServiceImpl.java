package com.ECommerce.services.serviceImpl;

import com.ECommerce.Entities.Cart;
import com.ECommerce.Entities.CartItem;
import com.ECommerce.Entities.Product;
import com.ECommerce.Entities.User;
import com.ECommerce.repository.CartItemRepository;
import com.ECommerce.repository.CartRepository;
import com.ECommerce.repository.ProductRepository;
import com.ECommerce.repository.UserRepository;
import com.ECommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart getCartByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        return cartRepository.findByUser(user);
    }

    @Override
    public Cart addProductToCart(Long userId, Long productId, int quantity) {
        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setQuantity(0);
            cart.setTotalAmount(0);
            cart = cartRepository.save(cart);
        }

        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice()); // store unit price
            cartItemRepository.save(cartItem);
            cart.getCartItems().add(cartItem);
        }

        recalculateCart(cart);

        return cartRepository.save(cart);
    }

    @Override
    public Cart removeProductFromCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElseThrow(() -> new RuntimeException("Product not in cart"));

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        recalculateCart(cart);

        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart clearCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        cartItemRepository.deleteByCart(cart);
        cart.getCartItems().clear();

        cart.setQuantity(0);
        cart.setTotalAmount(0);

        return cartRepository.save(cart);
    }

    @Override
    public void recalculateCart(Cart cart) {
        int totalQuantity = 0;
        double totalAmount = 0;

        for (CartItem item : cart.getCartItems()) {
            totalQuantity += item.getQuantity();
            totalAmount += item.getPrice() * item.getQuantity();
        }

        cart.setQuantity(totalQuantity);
        cart.setTotalAmount(totalAmount);
    }

}
