package com.learning.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.entity.InventoryItem;
import com.learning.entity.InventoryOrder;
import com.learning.entity.OrderStatus;
import com.learning.entity.Product;
import com.learning.event.Event;
import com.learning.event.EventType;
import com.learning.exception.InsufficientItemStockException;
import com.learning.repository.InventoryItemRepository;
import com.learning.repository.InventoryOrderRepository;
import com.learning.repository.ProductRepository;
import com.learning.rest.resource.OrderProduct;
import com.learning.rest.resource.OrderResource;
import com.learning.service.InventoryService;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amits on 28/09/15.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryOrderRepository inventoryOrderRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;
    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    @Transactional
    public InventoryOrder createInventoryOrder(OrderResource orderResource) throws InsufficientItemStockException {
        InventoryOrder inventoryOrder = new InventoryOrder();
        inventoryOrder.setOrderId(orderResource.getId());
        Product product = productRepository.findOne(orderResource.getOrderProduct().getProductId());
        inventoryOrder.setProduct(product);
        inventoryOrder.setStatus(OrderStatus.valueOf(orderResource.getStatus()));
        inventoryOrder.setQuantity(orderResource.getOrderProduct().getQuantity());
        inventoryOrder = inventoryOrderRepository.save(inventoryOrder);
        notifyListeners(inventoryOrder,EventType.INVENTORY_UPDATED);

        InventoryItem inventoryItem = inventoryItemRepository.findByProductId(orderResource.getOrderProduct().getProductId());
        inventoryItem.reduceItemStock(orderResource.getOrderProduct().getQuantity());
        return inventoryOrder;
    }

    private void notifyListeners(InventoryOrder inventoryOrder, EventType eventType) {
        Event event = new Event();
        event.setEventType(eventType);
        event.setEventId(inventoryOrder.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message=objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        producerTemplate.sendBody("activemq:topic:microservice", ExchangePattern.InOnly, message);
    }
}
