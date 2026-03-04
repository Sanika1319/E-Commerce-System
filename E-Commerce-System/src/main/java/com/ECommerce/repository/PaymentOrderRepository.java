package com.ECommerce.repository;

import com.ECommerce.Entities.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder,Long> {

}
