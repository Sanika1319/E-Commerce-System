package com.ECommerce.services.serviceImpl;

import com.ECommerce.Entities.*;
import com.ECommerce.repository.CartRepository;
import com.ECommerce.repository.PaymentRepository;
import com.ECommerce.repository.UserRepository;
import com.ECommerce.services.OrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    @Autowired
    private CartRepository cartRepository;



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Map<String, Object> createRazorpayOrder(Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

            Cart cart = cartRepository.findByUser(user);

            int amountInPaise = (int) (cart.getTotalAmount() * 100);

            RazorpayClient client =
                    new RazorpayClient(razorpayKey, razorpaySecret);

            JSONObject options = new JSONObject();
            options.put("amount", amountInPaise);
            options.put("currency", "INR");
            options.put("receipt", "txn_" + System.currentTimeMillis());

           Order order =client.orders.create(options);

            Map<String, Object> response = new HashMap<>();
            response.put("orderId", order.get("id"));
            response.put("amount", amountInPaise);
            response.put("key", razorpayKey);

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Razorpay order creation failed");
        }
    }

    // VERIFY PAYMENT & PLACE ORDER



    public RazorpayClient getClient() {
        try {
            return new RazorpayClient(razorpayKey, razorpaySecret);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Razorpay client");
        }
    }

    public void verifyPaymentAndSave(Orders order, Map<String, String> payload) {
        try {
            JSONObject data = new JSONObject();
            data.put("razorpay_order_id", payload.get("razorpay_order_id"));
            data.put("razorpay_payment_id", payload.get("razorpay_payment_id"));
            data.put("razorpay_signature", payload.get("razorpay_signature"));

            boolean isValid = Utils.verifyPaymentSignature(data, razorpaySecret);

            if (!isValid) {
                throw new RuntimeException("Invalid payment signature");
            }

            Payment payment = new Payment();
            payment.setRazorpayOrderId(payload.get("razorpay_order_id"));
            payment.setRazorpayPaymentId(payload.get("razorpay_payment_id"));
            payment.setRazorpaySignature(payload.get("razorpay_signature"));
            payment.setAmount(order.getTotalAmount());
            payment.setStatus(Status.PAID);
            payment.setCreatedAt(LocalDateTime.now());
            payment.setUser(order.getUser());
            payment.setOrders(order);

            paymentRepository.save(payment);

        } catch (Exception e) {
            throw new RuntimeException("Payment verification failed: " + e.getMessage());
        }
    }
}
