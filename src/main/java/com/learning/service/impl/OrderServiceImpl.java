package com.learning.service.impl;

import com.learning.entity.Order;
import com.learning.repository.OrderRepository;
import com.learning.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ArindamN on 29/09/2015.
 */
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
