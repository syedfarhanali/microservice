package com.leaning.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.OrderManagementServiceStarter;
import com.learning.controller.OrderController;
import com.learning.rest.resource.OrderResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by amits on 27/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderManagementServiceStarter.class)
@TransactionConfiguration(defaultRollback = true)
public class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @Test
    public void testOrderCreation() throws Exception {
        OrderResource orderResource = new OrderResource();
        orderResource.setBillingAddressId(1L);
        orderResource.setCustomerId(1L);
        orderResource.setProductId(1L);
        orderResource.setQuantity(4);
        ObjectMapper
                objectMapper = new ObjectMapper();
        String resource = objectMapper.writeValueAsString(orderResource);
        System.out.println(resource);

        orderController.create(orderResource);
    }
}
