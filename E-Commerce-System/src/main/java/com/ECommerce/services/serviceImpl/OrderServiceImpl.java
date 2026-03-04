package com.ECommerce.services.serviceImpl;

import com.ECommerce.repository.OrdersRepository;
import com.ECommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersRepository ordersRepository;



}
