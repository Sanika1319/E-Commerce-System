package com.ECommerce.repository;

import com.ECommerce.Entities.Orders;
import com.ECommerce.Entities.Status;
import com.ECommerce.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByUserId(Long userId);

    // All orders by status
    List<Orders> findByStatus(Status status);

    // Orders by user and status (recommended)
    List<Orders> findByUserAndStatus(User user, Status status);

    List<Orders> findByStatusAndStatusUpdatedAtBefore(
            Status status, LocalDateTime time);
}
