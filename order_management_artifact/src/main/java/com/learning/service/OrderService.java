package com.learning.service;

import com.learning.entity.Order;
import com.learning.rest.resource.OrderResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amits on 27/09/15.
 */
public interface OrderService {
    @Transactional
    Order createOrder(OrderResource orderResource);

}
