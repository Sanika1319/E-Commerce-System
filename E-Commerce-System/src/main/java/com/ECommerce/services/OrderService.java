package com.ECommerce.services;

import com.ECommerce.Entities.Orders;
import com.ECommerce.Entities.Status;
import com.razorpay.RazorpayException;
import java.util.List;
import java.util.Map;

public interface OrderService {

    Orders placeOrder(Long userId);

    Orders getOrderById(Long orderId);

    List<Orders> getOrdersByUser(Long userId);

    List<Orders> getAllOrders();


//    String verifyPayment(Long orderId,
//                         String razorpayPaymentId,
//                         String razorpayOrderId,
//                         String razorpaySignature);


    List<Orders> getPlacedOrders();

    List<Orders> getOnTheWayOrders();

    List<Orders> getDeliveredOrders();
    List<Orders> getCancelledOrders();


    // CANCEL
    void cancelOrder(Long orderId);

}
