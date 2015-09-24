package com.learning.dto;

import com.learning.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by amits on 24/09/15.
 */
@Getter
@Setter
public class PurchaseOrderResponse {

    private Long orderId;

    private Long customerId;

    private String CustomerName;

    private OrderStatus orderStatus;

    private Date orderDate;

    private Long productId;

    private Long billingAddressId;

    private Integer productQuantity;
}
