package com.learning.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.entity.BaseEntity;
import com.learning.entity.Vendor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by amits on 24/09/15.
 */
@Setter
@Getter
public class ProductDetailResource {

    private String color;


    private String modelNumber;

    private Date manufacturingDate;

    @JsonIgnore
    private Vendor vendor;

    @JsonIgnore
    private ProductResource product;
}
