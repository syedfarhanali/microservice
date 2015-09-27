package com.learning.projection;

import com.learning.entity.Category;
import com.learning.entity.Product;
import com.learning.entity.ProductDetail;
import com.learning.entity.ProductPricing;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by amits on 27/09/15.
 */
@Projection(name = "inlineProductDetails", types = {Product.class})
public interface InlineProductDetails {

    String getName();

    String getDescription();

    Category getCategory();

    ProductDetail getProductDetail();

    ProductPricing getProductPricing();

}
