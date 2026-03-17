package com.ECommerce.Schedular;


import com.ECommerce.Entities.Orders;
import com.ECommerce.Entities.Status;
import com.ECommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UpdateOrderStatus {


    @Autowired
    private OrdersRepository ordersRepository;

    @Transactional
    @Scheduled(fixedRate = 300000) // every 5 min
    public void updateOrderStatus() {

        LocalDateTime now = LocalDateTime.now();

        // PLACED → ON_THE_WAY after 5 min
        List<Orders> placedOrders =
                ordersRepository.findByStatusAndStatusUpdatedAtBefore(
                        Status.PLACED, now.minusMinutes(5));

        for (Orders order : placedOrders) {
            order.setStatus(Status.ON_THE_WAY);
            order.setStatusUpdatedAt(now);
        }
        ordersRepository.saveAll(placedOrders);

        // ON_THE_WAY → DELIVERED after 15 min
        List<Orders> onTheWayOrders =
                ordersRepository.findByStatusAndStatusUpdatedAtBefore(
                        Status.ON_THE_WAY, now.minusMinutes(15));

        for (Orders order : onTheWayOrders) {
            order.setStatus(Status.DELIVERED);
            order.setStatusUpdatedAt(now);
        }
        ordersRepository.saveAll(onTheWayOrders);
    }

}
