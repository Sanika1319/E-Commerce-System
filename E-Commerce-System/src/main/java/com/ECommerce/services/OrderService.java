package com.ECommerce.services;

import com.ECommerce.Entities.Orders;
import com.ECommerce.Entities.Status;
import com.razorpay.RazorpayException;
import java.util.List;
import java.util.Map;

public interface OrderService {

    Map<String, Object> createOrder(Long userId) throws RazorpayException;
    Orders getOrderById(Long orderId);

    List<Orders> getOrdersByUser(Long userId);

    List<Orders> getAllOrders();

    Orders updateOrderStatus(Long orderId, Status status);

    void deleteOrder(Long orderId);
    String verifyPayment(Long orderId,
                         String razorpayPaymentId,
                         String razorpayOrderId,
                         String razorpaySignature);

}
