package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by amits on 24/09/15.
 */
@Entity
@Getter
@Setter
public class Payment extends BaseEntity{

    private Long transactionId;

    private Long paymentServiceId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
