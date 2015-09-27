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
public class Order extends BaseEntity{

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Date orderDate;

    @JoinColumn(name = "address_id")
    @OneToOne
    private Address billingAddress;

    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToOne(mappedBy = "order")
    private Shipment shipment;

    private int quantity;

    private String description;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
