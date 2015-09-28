package com.learning.rest;

import com.learning.entity.ProductDetail;
import lombok.Getter;
import lombok.Setter;

import javax.naming.ldap.PagedResultsControl;
import java.util.Date;

/**
 * Created by amits on 27/09/15.
 */
@Getter
@Setter
public class ProductRestResource {

    private Long productId;

    private String name;

    private String description;

    private Long categoryId;

    private Long categoryName;

    private Date productManufacturingDate;

}
