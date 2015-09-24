package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by amits on 21/09/15.
 */
@Entity
@Setter
@Getter
public class Product extends BaseEntity{

    private String name;

    private double price;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @OneToOne(mappedBy = "product")
    private ProductDetail productDetail;

}
