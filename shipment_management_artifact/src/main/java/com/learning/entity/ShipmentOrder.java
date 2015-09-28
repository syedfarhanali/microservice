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
@Table(name = "shipment_order")
public class ShipmentOrder extends BaseEntity{

    private Long orderId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String vendor;

    private double payment;

}
