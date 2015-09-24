package com.learning.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by amits on 23/09/15.
 */
@Setter
@Getter
public class ProductDto {

    private Long productId;

    private String color;

    private String modelNumber;

    private Date productionDate;

    private Date purchaseDate;

    private String productName;
}
