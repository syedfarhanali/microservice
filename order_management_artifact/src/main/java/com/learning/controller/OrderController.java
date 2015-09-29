package com.learning.controller;

import com.learning.entity.Order;
import com.learning.repository.OrderRepository;
import com.learning.rest.resource.CreateOrderRequest;
import com.learning.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by amits on 27/09/15.
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order getOne(@PathVariable("id") Long id) {
        return orderRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Order create(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.createOrder(createOrderRequest);

    }


}
