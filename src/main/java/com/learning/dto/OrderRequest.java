package com.learning.dto;

import com.learning.entity.Address;
import com.learning.entity.Customer;
import com.learning.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by amits on 24/09/15.
 */
@Getter
@Setter
public class OrderRequest {

    private Long customerId;

    private Date orderDate;

    private Long productId;

    private Long billingAddressId;

    private Integer productQuantity;

}
