package com.ECommerce.Controller;


import com.ECommerce.Entities.Orders;
import com.ECommerce.services.OrderService;
import com.ECommerce.services.serviceImpl.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    // STEP 1: Create Razorpay Order
    @PostMapping("/create/{userId}")
    public Map<String, Object> createOrder(@PathVariable Long userId) {
        return paymentService.createRazorpayOrder(userId);
    }

    // STEP 2: Verify payment & place order
    @PostMapping("/verify/{userId}")
    public String verifyPayment(
            @PathVariable Long userId,
            @RequestBody Map<String, String> payload
    ) {

        // ✅ 1. Create order FIRST
        Orders order = orderService.placeOrder(userId);

        // ✅ 2. Verify payment & save
        paymentService.verifyPaymentAndSave(order, payload);

        return "Payment Successful & Order Placed";
    }
}
