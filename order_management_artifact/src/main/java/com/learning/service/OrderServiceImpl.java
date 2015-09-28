package com.learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.entity.*;
import com.learning.event.Event;
import com.learning.event.EventType;
import com.learning.repository.OrderCustomerRepository;
import com.learning.repository.OrderRepository;
import com.learning.rest.resource.AddressResource;
import com.learning.rest.resource.CustomerResource;
import com.learning.rest.resource.OrderResource;
import com.learning.rest.resource.ProductResource;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by amits on 27/09/15.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderCustomerRepository orderCustomerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Transactional
    @Override
    public Order createOrder(OrderResource orderResource) {
        Long customerId = orderResource.getCustomerId();
        ResponseEntity<CustomerResource> responseEntity = restTemplate.getForEntity("http://localhost:9090/customer/{id}", CustomerResource.class, customerId);
        CustomerResource customer = responseEntity.getBody();
        Long productId = orderResource.getProductId();
        ResponseEntity<ProductResource> productResponseEntity = restTemplate.getForEntity("http://localhost:9091/product/{id}", ProductResource.class, productId);
        ProductResource product = productResponseEntity.getBody();
        Order order = new Order();
        order.setDescription("First order");
        Address billingAddress = convertToEntity(customer.getAddresses().get(0));
        order.setBillingAddress(billingAddress);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROCESS);
        OrderProduct orderProduct = convertToEntity(product);
        orderProduct.setQuantity(orderResource.getQuantity());
        order.setOrderProduct(orderProduct);
        OrderCustomer orderCustomer = convertToEntity(customer);
        List<Order> oldOrders = orderCustomer.getOrders();
        if (oldOrders == null) {
            oldOrders = new ArrayList<>();
        }
        oldOrders.add(order);
        orderCustomer.setOrders(oldOrders);
        order.setOrderCustomer(orderCustomer);
        orderCustomerRepository.save(orderCustomer);

        EventType eventType = EventType.ORDER_PLACED;
        Order savedOrder = orderRepository.save(order);
        notifyListeners(order,eventType);
        return savedOrder;
    }

    private void notifyListeners(Order order, EventType eventType) {
        Event event = new Event();
        event.setEventType(eventType);
        event.setEventId(order.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message=objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        producerTemplate.sendBody("activemq:topic:microservice", ExchangePattern.InOnly, message);
    }

    private OrderCustomer convertToEntity(CustomerResource customer) {
        OrderCustomer orderCustomer = new OrderCustomer();
        orderCustomer.setCustomerId(customer.getCustomerId());
        orderCustomer.setEmail(customer.getEmail());
        return orderCustomer;
    }

    private Address convertToEntity(AddressResource addressResource) {
        Address address = new Address();
        address.setCity(addressResource.getCity());
        address.setLocality(addressResource.getLocality());
        address.setPinCode(addressResource.getPinCode());
        address.setState(addressResource.getState());
        return address;
    }

    private OrderProduct convertToEntity(ProductResource productResource) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(productResource.getId());
        orderProduct.setName(productResource.getName());
        orderProduct.setPrice(productResource.getProductPricing().getPrice());
        Vendor vendor = productResource.getProductDetail().getVendor();

        if (vendor != null) {
            orderProduct.setVendor(vendor.getName());
        }
        return orderProduct;
    }

}
