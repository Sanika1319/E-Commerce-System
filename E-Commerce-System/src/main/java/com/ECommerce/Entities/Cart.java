package com.ECommerce.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private int quantity;
    private double totalAmount;

    @OneToOne
    private User user;
    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();
}
