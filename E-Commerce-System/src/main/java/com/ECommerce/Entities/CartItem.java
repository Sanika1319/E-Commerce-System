package com.ECommerce.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnoreProperties("cartItems")   // 🔥 prevent infinite loop
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"cartItems", "orderItems", "category"})
    private Product product;
    private int quantity;

    private double price;
}
