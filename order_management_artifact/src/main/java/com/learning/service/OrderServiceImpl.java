package com.learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.entity.*;
import com.learning.event.Event;
import com.learning.event.EventType;
import com.learning.repository.OrderCustomerRepository;
import com.learning.repository.OrderRepository;
import com.learning.rest.resource.CreateOrderRequest;
import com.learning.rest.resource.customer.AddressBean;
import com.learning.rest.resource.customer.CustomerBean;
import com.learning.rest.resource.product.ProductBean;
import com.learning.rest.resource.product.VendorBean;
import com.learning.rest.resource.shipment.ShipmentBean;
import com.learning.rest.resource.shipment.ShipmentOrderBean;
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
    public Order createOrder(CreateOrderRequest createOrderRequest) {
        CustomerBean customerBean = getCustomerBean(createOrderRequest);
        ProductBean productBean = getProductBean(createOrderRequest);
        AddressBean addressBean = getAddressBean(createOrderRequest);

        Order order = new Order();
        order.setDescription("First order");
        Address billingAddress = convertToEntity(addressBean);
        order.setBillingAddress(billingAddress);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROCESS);
        OrderProduct orderProduct = convertToEntity(productBean);
        orderProduct.setQuantity(createOrderRequest.getQuantity());
        order.setOrderProduct(orderProduct);
        OrderCustomer orderCustomer = convertToEntity(customerBean);
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
        notifyListeners(order, eventType);
        StringBuilder stringBuilder = new StringBuilder("Order created for user ").append(orderCustomer.getName()).append(" for product ").append(orderProduct.getName()).append(" and for quantity ").append(orderProduct.getQuantity());
        System.out.println(stringBuilder);
        return savedOrder;
    }

    @Override
    @Transactional
    public void updateOrder(ShipmentBean shipmentBean, OrderStatus orderStatus) {
        ShipmentOrderBean shipmentOrderBean = shipmentBean.getShipmentOrder();
        Long orderId = shipmentOrderBean.getOrderId();
        Order order = orderRepository.findOne(orderId);
        order.setStatus(orderStatus);
        notifyListeners(order, EventType.ORDER_SUCCESS);
        StringBuilder stringBuilder = new StringBuilder("Order completed for user ").append(order.getOrderCustomer().getName()).append(" for product ").append(order.getOrderProduct().getName());
        System.out.println(stringBuilder);
    }

    private AddressBean getAddressBean(CreateOrderRequest createOrderRequest) {
        Long billingAddressId = createOrderRequest.getBillingAddressId();
        ResponseEntity<AddressBean> responseEntity = restTemplate.getForEntity("http://localhost:9090/address/{id}", AddressBean.class, billingAddressId);
        return responseEntity.getBody();
    }

    private ProductBean getProductBean(CreateOrderRequest createOrderRequest) {
        Long productId = createOrderRequest.getProductId();
        ResponseEntity<ProductBean> productResponseEntity = restTemplate.getForEntity("http://localhost:9091/product/{id}", ProductBean.class, productId);
        return productResponseEntity.getBody();
    }

    private CustomerBean getCustomerBean(CreateOrderRequest createOrderRequest) {
        Long customerId = createOrderRequest.getCustomerId();
        ResponseEntity<CustomerBean> responseEntity = restTemplate.getForEntity("http://localhost:9090/customer/{id}", CustomerBean.class, customerId);
        return responseEntity.getBody();
    }

    private void notifyListeners(Order order, EventType eventType) {
        Event event = new Event();
        event.setEventType(eventType);
        event.setEventId(order.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        producerTemplate.sendBody("activemq:topic:microservice", ExchangePattern.InOnly, message);
    }

    private OrderCustomer convertToEntity(CustomerBean customer) {
        OrderCustomer orderCustomer = new OrderCustomer();
        orderCustomer.setCustomerId(customer.getId());
        orderCustomer.setEmail(customer.getEmail());
        orderCustomer.setName(customer.getFirstName() + " " + customer.getLastName());
        return orderCustomer;
    }

    private Address convertToEntity(AddressBean addressResource) {
        Address address = new Address();
        address.setCity(addressResource.getCity());
        address.setLocality(addressResource.getLocality());
        address.setPinCode(addressResource.getPinCode());
        address.setState(addressResource.getState());
        return address;
    }

    private OrderProduct convertToEntity(ProductBean productBean) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(productBean.getId());
        orderProduct.setName(productBean.getName());
        orderProduct.setPrice(productBean.getProductPricing().getPrice());
        VendorBean vendorBean = productBean.getProductDetail().getVendor();

        if (vendorBean != null) {
            orderProduct.setVendor(vendorBean.getName());
        }
        return orderProduct;
    }

}
