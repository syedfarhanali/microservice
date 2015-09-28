package com.learning.listener;

import com.learning.event.Event;
import com.learning.event.EventType;
import com.learning.exception.InsufficientItemStockException;
import com.learning.handler.EventHandler;
import com.learning.rest.resource.OrderResource;
import com.learning.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by amits on 28/09/15.
 */
@Component("inventoryEventHandler")
public class InventoryEventHandler implements EventHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public void handleEvent(Event event) {
        EventType eventType = event.getEventType();
        Long eventId = event.getEventId();
        if (EventType.ORDER_PLACED.equals(eventType)) {
            OrderResource orderResource = fetchOrderDetails(eventId);
            try {
                inventoryService.createInventoryOrder(orderResource);
            } catch (InsufficientItemStockException e) {
                e.printStackTrace();
            }
            handleOrderCreation(event.getEventId());
        } else if (EventType.ORDER_FAILED.equals(eventType)) {
            handleOrderFailure(event.getEventId());
        } else if (EventType.ORDER_SUCCESS.equals(eventType)) {
            handleOrderSuccess(event.getEventId());
        }
    }

    private void handleOrderFailure(Long orderId) {
        OrderResource orderResource = fetchOrderDetails(orderId);



    }

    private OrderResource fetchOrderDetails(Long orderId) {
        ResponseEntity<OrderResource> responseEntity = restTemplate.getForEntity("http://localhost:9092/order/{id}", OrderResource.class, orderId);
        OrderResource orderResource = responseEntity.getBody();
        return orderResource;
    }

    private void handleOrderSuccess(Long orderId) {
        OrderResource orderResource = fetchOrderDetails(orderId);
    }

    private void handleOrderCreation(Long orderId) {
        OrderResource orderResource = fetchOrderDetails(orderId);
        System.out.println("Order placed with id :"+orderResource.getId());
        System.out.println("Order placed by :"+orderResource.getOrderCustomer().getEmail());
    }
}
