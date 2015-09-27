package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by amits on 24/09/15.
 */
@Entity
@Getter
@Setter
public class Shipment extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address shipmentAddress;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "logistic_id")
    private Logistic logistic;

}
