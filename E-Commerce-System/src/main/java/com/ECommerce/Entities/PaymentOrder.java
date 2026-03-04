package com.ECommerce.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

    private double amount;

    @Enumerated(EnumType.STRING)
    private Status status;


    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @OneToOne
    private Orders orders;
}
