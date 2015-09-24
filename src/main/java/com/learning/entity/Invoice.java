package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by amits on 22/09/15.
 */
@Entity
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue
    private Long invoiceId;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    private Integer totalPrice;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @JoinColumn(name = "order_id")
    @OneToOne
    private PurchaseOrder referencedPurchaseOrder;
}
