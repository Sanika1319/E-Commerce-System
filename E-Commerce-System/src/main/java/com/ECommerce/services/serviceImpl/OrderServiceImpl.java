package com.ECommerce.services.serviceImpl;

import com.ECommerce.Entities.*;

import com.ECommerce.repository.*;
import com.ECommerce.services.OrderService;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;
import com.ECommerce.Entities.Payment;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private AddressRepository addressRepository;

//    @Autowired
//    private RazorpayService razorpayService;

    @Autowired
    private  PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public Orders placeOrder(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found : " + userId));
        Cart cart = cartRepository.findByUser(user);
        if (cart == null || cart.getQuantity()== 0){
            throw new RuntimeException("Cart is empty");
        }


//        /create order

        Orders order = new Orders();
        order.setUser(user);
        order.setTotalAmount(cart.getTotalAmount());
        order.setDate(LocalDate.now());
        order.setStatus(Status.PLACED);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatusUpdatedAt(LocalDateTime.now());
        Orders savedOrders = ordersRepository.save(order);

        //  Clear cart after order
        cart.getCartItems().clear();
        cart.setQuantity(0);
        cart.setTotalAmount(0);
        cartRepository.save(cart);

        // 🔥 DELETE requires transaction
        cartItemRepository.deleteByCart(cart);

        return savedOrders;
    }

    @Override
    public Orders getOrderById(Long orderId) {
        return ordersRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: "+orderId));
    }

    @Override
    public List<Orders> getOrdersByUser(Long userId) {
        return ordersRepository.findByUserId(userId);
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }



    @Override
    public List<Orders> getPlacedOrders() {
        return ordersRepository.findByStatus(Status.PLACED);
    }

    @Override
    public List<Orders> getOnTheWayOrders() {
        return ordersRepository.findByStatus(Status.ON_THE_WAY);
    }

    @Override
    public List<Orders> getDeliveredOrders() {
        return ordersRepository.findByStatus(Status.DELIVERED);
    }

    @Override
    public List<Orders> getCancelledOrders() {
        return ordersRepository.findByStatus(Status.CANCELLED);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Orders orders = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order Not Found: " + orderId));

        if (orders.getStatus() == Status.CANCELLED ||
                orders.getStatus() == Status.DELIVERED) {
            throw new RuntimeException("Delivered or cancelled order cannot be cancelled");
        }

        // ✅ FIXED: correct variable
        Payment payment = orders.getPayment();

        try {
            if (payment != null && payment.getStatus() == Status.PAID) {

                RazorpayClient razorpay = paymentService.getClient();

                JSONObject refundRequest = new JSONObject();

                int amountInPaise = (int) (payment.getAmount() * 100);
                refundRequest.put("amount", amountInPaise);

                Refund refund = razorpay.payments.refund(
                        payment.getRazorpayPaymentId(),
                        refundRequest
                );

                // ✅ Update payment
                payment.setStatus(Status.REFUNDED);
                payment.setRazorpayRefundId(refund.get("id").toString());

                paymentRepository.save(payment);

                System.out.println("Refund Response: " + refund.toString());
            }

        } catch (Exception e) {
            throw new RuntimeException("Refund failed: " + e.getMessage());
        }

        orders.setStatus(Status.CANCELLED);
        orders.setStatusUpdatedAt(LocalDateTime.now());

        ordersRepository.save(orders);
    }


}
