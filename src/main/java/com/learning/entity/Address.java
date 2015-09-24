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
public class Address {

    @Id
    @GeneratedValue
    private Long addressId;

    private String locality;
    private String street;
    private String city;
    private String state;
    private String pinCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
