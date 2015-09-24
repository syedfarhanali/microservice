package com.learning.service;

import com.learning.dto.OrderRequest;
import com.learning.dto.OrderResponse;
import com.learning.exception.InsufficientItemStockException;
import com.learning.exception.ItemNotFoundException;

/**
 * Created by amits on 24/09/15.
 */
public interface PurchaseOrderService {

    public OrderResponse placeOrder(OrderRequest orderRequest) throws ItemNotFoundException, InsufficientItemStockException;
}
