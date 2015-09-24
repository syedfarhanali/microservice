package com.learning.service.impl;

import com.learning.dto.OrderRequest;
import com.learning.dto.OrderResponse;
import com.learning.entity.*;
import com.learning.exception.InsufficientItemStockException;
import com.learning.exception.ItemNotFoundException;
import com.learning.repository.*;
import com.learning.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

/**
 * Created by amits on 24/09/15.
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private LogisticRepository logisticRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) throws ItemNotFoundException, InsufficientItemStockException {
        Long productId = orderRequest.getProductId();
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new ItemNotFoundException("No item found for id :" + productId);
        }
        Long customerId = orderRequest.getCustomerId();
        Customer customer = customerRepository.findOne(customerId);

        Address address = addressRepository.findOne(orderRequest.getBillingAddressId());

        Order order = new Order();
        order.setDescription("First purchase order.");
        order.setProduct(product);
        order.setCustomer(customer);
        order.setBillingAddress(address);
        order.setOrderDate(orderRequest.getOrderDate());
        order.setStatus(OrderStatus.COMPLETED);
        order = orderRepository.save(order);

        Invoice invoice = new Invoice();
        invoice.setPaymentStatus(PaymentStatus.COMPLETED);
        invoice.setCustomer(customer);
        invoice.setOrder(order);
        invoice.setTotalPrice(product.getPrice()*orderRequest.getProductQuantity());
        invoiceRepository.save(invoice);

        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventory.reduceItemStock(orderRequest.getProductQuantity());
        Logistic logistic = logisticRepository.findOne(1L);

        Payment payment = new Payment();
        payment.setPaymentServiceId(1L);
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        payment.setTransactionId(new Random().nextLong());

        paymentRepository.save(payment);

        Shipment shipment = new Shipment();
        shipment.setShipmentAddress(address);
        shipment.setStatus(DeliveryStatus.COMPLETED);
        shipment.setProduct(product);
        shipment.setOrder(order);
        shipment.setLogistic(logistic);
        shipmentRepository.save(shipment);


        return preparePurchaseOrderResponse(orderRequest, order);
    }

    private OrderResponse preparePurchaseOrderResponse(OrderRequest orderRequest, Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setBillingAddressId(orderRequest.getBillingAddressId());
        orderResponse.setCustomerId(orderRequest.getCustomerId());
        orderResponse.setProductId(orderRequest.getProductId());
        orderResponse.setOrderStatus(order.getStatus());
        orderResponse.setProductQuantity(orderRequest.getProductQuantity());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setCustomerName(order.getCustomer().getName());
        orderResponse.setOrderId(order.getId());
        return orderResponse;
    }


}
