package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amits on 21/09/15.
 */
@Entity
@Getter
@Setter
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Long orderId;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Date orderDate;

    @JoinColumn(name = "address_id")
    @OneToOne
    private Address billingAddress;

    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;

}
