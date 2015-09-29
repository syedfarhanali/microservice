package com.learning.service.impl;

import com.learning.dto.OrderRequest;
import com.learning.dto.OrderResponse;
import com.learning.entity.*;
import com.learning.exception.InsufficientItemStockException;
import com.learning.exception.ItemNotFoundException;
import com.learning.repository.*;
import com.learning.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

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
    private LogisticRepository logisticRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InvoiceService invoiceService;

//    @Autowired
//    private VendorRepository vendorRepository;

    private static final String ORDER_SUCCESS_MSG = "Order placed";
    private static final String ORDER_FAILED_MSG = "Unable to validate order";


    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) throws ItemNotFoundException, InsufficientItemStockException {

        Product product = getOrderFromRequest(orderRequest);
        if (validateOrderRequest(orderRequest, product)) {
            Customer customer = getCustomerFromOrderRequest(orderRequest);
            Address address = getAddressFromOrderRequest(orderRequest);
            Double price = getPrice(product, orderRequest);

            Payment payment = createPayment(price);
            Order order = createOrder(product,
                    orderRequest, customer, address, payment);
            createInvoice(customer, order, price);
            reduceItemStock(product.getId(), orderRequest.getProductQuantity());
            createShipment(address, product, order);

            return preparePurchaseOrderResponse(orderRequest, order);
        } else {
            return prepareFailedOrderResponse();
        }
    }


    private Product getOrderFromRequest(OrderRequest orderRequest) {
        Long productId = orderRequest.getProductId();
        return productRepository.findOne(productId);
    }

    private boolean validateOrderRequest(OrderRequest orderRequest, Product product) throws ItemNotFoundException, InsufficientItemStockException {
        if (product == null) {
            throw new ItemNotFoundException("No item found for id :" + product.getId());
        } else if (orderRequest.getProductQuantity() > 0) {

        }
        return true;
    }

    private Customer getCustomerFromOrderRequest(OrderRequest orderRequest) {
        Long customerId = orderRequest.getCustomerId();
        Customer customer = customerRepository.findOne(customerId);
        return customer;
    }

    private Address getAddressFromOrderRequest(OrderRequest orderRequest) {
        return addressRepository.findOne(orderRequest.getBillingAddressId());
    }

    private Double getPrice(Product product, OrderRequest orderRequest) {
        return product.getPrice() * orderRequest.getProductQuantity();
    }

    private Payment createPayment(Double amount) {
        Payment payment = new Payment();
        payment.setPaymentServiceId(1L);
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        payment.setTransactionId(new Random().nextLong());
        return paymentService.createPayment(payment);
    }

    private Order createOrder(Product product,
                              OrderRequest orderRequest,
                              Customer customer,
                              Address address,
                              Payment payment) {
        Order order = new Order();
        order.setDescription("First purchase order.");
        order.setProduct(product);
        order.setQuantity(orderRequest.getProductQuantity());
        order.setCustomer(customer);
        order.setBillingAddress(address);
        order.setOrderDate(orderRequest.getOrderDate());
        order.setStatus(OrderStatus.COMPLETED);
        order.setPayment(payment);
        order.setVendor(product.getProductDetail().getVendor());
        order = orderService.createOrder(order);
        return order;
    }

    private void createInvoice(Customer customer,
                               Order order,
                               Double totalPrice) {
        Invoice invoice = new Invoice();
        invoice.setPaymentStatus(PaymentStatus.COMPLETED);
        invoice.setCustomer(customer);
        invoice.setOrder(order);
        invoice.setTotalPrice(totalPrice);
        invoiceService.createInvoice(invoice);
    }

    private void reduceItemStock(long productId, Integer productQuantity) throws InsufficientItemStockException {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventory.reduceItemStock(productQuantity);
    }

    private void createShipment(Address address,
                                Product product,
                                Order order) {
        Logistic logistic = logisticRepository.findOne(1L);
        Shipment shipment = new Shipment();
        shipment.setShipmentAddress(address);
        shipment.setStatus(DeliveryStatus.COMPLETED);
        shipment.setProduct(product);
        shipment.setOrder(order);
        shipment.setLogistic(logistic);
        shipmentService.createShipment(shipment);
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
        orderResponse.setStatus(OrderStatus.PLACED);
        orderResponse.setMessage(ORDER_SUCCESS_MSG);
        return orderResponse;
    }

    private OrderResponse prepareFailedOrderResponse() {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatus(OrderStatus.FAILED);
        orderResponse.setMessage(ORDER_FAILED_MSG);
        return orderResponse;
    }


}
