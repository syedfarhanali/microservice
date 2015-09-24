package com.learning;

import com.learning.dto.OrderRequest;
import com.learning.dto.OrderResponse;
import com.learning.service.PurchaseOrderService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by amits on 24/09/15.
 */
public class OrderServiceTest extends BaseTest {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Test
    public void testPlacingOrderForExitingProduct() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomerId(1L);
        orderRequest.setOrderDate(DateTime.now().toDate());
        orderRequest.setProductId(2L);
        orderRequest.setProductQuantity(3);
        orderRequest.setBillingAddressId(2L);
        OrderResponse orderResponse = purchaseOrderService.placeOrder(orderRequest);
        Assert.assertTrue(orderResponse.getOrderId() > 0);
    }
}
