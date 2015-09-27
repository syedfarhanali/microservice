package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by amits on 25/09/15.
 */
@Entity
@Setter
@Getter
public class ProductPricing extends BaseEntity{

    private double price;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double discount;
}
