package com.ECommerce.Controller;

import com.ECommerce.Entities.Orders;
import com.ECommerce.Entities.Status;
import com.ECommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Orders")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("placeOrder/{userId}")
    public ResponseEntity<Orders> placeOrder(@PathVariable Long userId){
        return new ResponseEntity<>(orderService.placeOrder(userId), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getByOrderId(@PathVariable Long orderId)
    {
        return new ResponseEntity<>(orderService.getOrderById(orderId),HttpStatus.OK    );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Orders>> getByUserId(@PathVariable Long userId)
    {
        return new ResponseEntity<>(orderService.getOrdersByUser(userId),HttpStatus.OK    );
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allOrders")
    public ResponseEntity<List<Orders>> getAllOrders()
    {
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK    );
    }

    @GetMapping("/getPlacedOrders")
    public ResponseEntity<List<Orders>> getPlacedOrders(){
        return new ResponseEntity<>(orderService.getPlacedOrders(),HttpStatus.OK);
    }
    @GetMapping("/getOnTheWayOrders")
    public ResponseEntity<List<Orders>> getOnTheWayOrders(){
        return new ResponseEntity<>(orderService.getOnTheWayOrders(),HttpStatus.OK);
    }

    @GetMapping("/getDeliveredOrders")
    public ResponseEntity<List<Orders>> getDeliveredOrders(){
        return new ResponseEntity<>(orderService.getDeliveredOrders(),HttpStatus.OK);
    }
    @GetMapping("/getCancelledOrders")
    public ResponseEntity<List<Orders>> getCancelledOrders(){
        return new ResponseEntity<>(orderService.getCancelledOrders(),HttpStatus.OK);
    }

    @DeleteMapping("/cancelOrder/{orderId}")
    public ResponseEntity<?>  cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order Canceled Successfully");
    }


}
