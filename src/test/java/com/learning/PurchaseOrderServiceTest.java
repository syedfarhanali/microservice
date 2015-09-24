package com.learning;

import com.learning.dto.PurchaseOrderRequest;
import com.learning.dto.PurchaseOrderResponse;
import com.learning.service.PurchaseOrderService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by amits on 24/09/15.
 */
public class PurchaseOrderServiceTest extends BaseTest {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Test
    public void testPlacingOrderForExitingProduct() throws Exception {
        PurchaseOrderRequest purchaseOrderRequest = new PurchaseOrderRequest();
        purchaseOrderRequest.setCustomerId(1L);
        purchaseOrderRequest.setOrderDate(DateTime.now().toDate());
        purchaseOrderRequest.setProductId(2L);
        purchaseOrderRequest.setProductQuantity(3);
        purchaseOrderRequest.setBillingAddressId(2L);
        PurchaseOrderResponse purchaseOrderResponse = purchaseOrderService.placeOrder(purchaseOrderRequest);
        Assert.assertTrue(purchaseOrderResponse.getOrderId() > 0);
    }
}
