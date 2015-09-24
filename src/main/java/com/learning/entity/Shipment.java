package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by amits on 24/09/15.
 */
@Entity

public class Shipment {

    @Id
    @GeneratedValue
    @Getter
    private Long shipmentId;

    @OneToOne
    @JoinColumn(name = "address_id")
    @Getter
    @Setter
    private Address deliveryAddress;

    @OneToOne
    @JoinColumn(name = "product_id")
    @Getter
    @Setter
    private Product itemShipped;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne
    @JoinColumn(name = "order_id")
    @Getter
    @Setter
    private PurchaseOrder associatedOrder;


}
