package com.learning.service.impl;

import com.learning.dto.PurchaseOrderRequest;
import com.learning.dto.PurchaseOrderResponse;
import com.learning.entity.*;
import com.learning.exception.InsufficientItemStockException;
import com.learning.exception.ItemNotFoundException;
import com.learning.repository.*;
import com.learning.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    @Transactional
    public PurchaseOrderResponse placeOrder(PurchaseOrderRequest purchaseOrderRequest) throws ItemNotFoundException, InsufficientItemStockException {
        Long productId = purchaseOrderRequest.getProductId();
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new ItemNotFoundException("No item found for id :" + productId);
        }
        Long customerId = purchaseOrderRequest.getCustomerId();
        Customer customer = customerRepository.findOne(customerId);

        Address address = addressRepository.findOne(purchaseOrderRequest.getBillingAddressId());

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProduct(product);
        purchaseOrder.setCustomer(customer);
        purchaseOrder.setBillingAddress(address);
        purchaseOrder.setOrderDate(purchaseOrderRequest.getOrderDate());
        purchaseOrder.setOrderStatus(OrderStatus.COMPLETED);
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        Inventory inventory = inventoryRepository.findByProductProductId(productId);
        inventory.removeItems(purchaseOrderRequest.getProductQuantity());

        return preparePurchaseOrderResponse(purchaseOrderRequest, purchaseOrder);
    }

    private PurchaseOrderResponse preparePurchaseOrderResponse(PurchaseOrderRequest purchaseOrderRequest, PurchaseOrder purchaseOrder) {
        PurchaseOrderResponse purchaseOrderResponse = new PurchaseOrderResponse();
        purchaseOrderResponse.setBillingAddressId(purchaseOrderRequest.getBillingAddressId());
        purchaseOrderResponse.setCustomerId(purchaseOrderRequest.getCustomerId());
        purchaseOrderResponse.setProductId(purchaseOrderRequest.getProductId());
        purchaseOrderResponse.setOrderStatus(purchaseOrder.getOrderStatus());
        purchaseOrderResponse.setProductQuantity(purchaseOrderRequest.getProductQuantity());
        purchaseOrderResponse.setOrderDate(purchaseOrder.getOrderDate());
        purchaseOrderResponse.setCustomerName(purchaseOrder.getCustomer().getName());
        purchaseOrderResponse.setOrderId(purchaseOrder.getOrderId());
        return purchaseOrderResponse;
    }
}
