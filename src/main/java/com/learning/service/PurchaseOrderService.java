package com.learning.service;

import com.learning.dto.PurchaseOrderRequest;
import com.learning.dto.PurchaseOrderResponse;
import com.learning.exception.InsufficientItemStockException;
import com.learning.exception.ItemNotFoundException;

/**
 * Created by amits on 24/09/15.
 */
public interface PurchaseOrderService {

    public PurchaseOrderResponse placeOrder(PurchaseOrderRequest purchaseOrderRequest) throws ItemNotFoundException, InsufficientItemStockException;
}
