package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amits on 21/09/15.
 */
@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue
    private Long productId;

    private String color;

    private String modelNumber;

    private Date productionDate;

    private Date purchaseDate;

    private String productName;

}
