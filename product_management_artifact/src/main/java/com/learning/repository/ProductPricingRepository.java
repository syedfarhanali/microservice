package com.learning.repository;

import com.learning.entity.ProductPricing;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by amits on 26/09/15.
 */
@RepositoryRestResource(path = "productPricings", collectionResourceRel = "productPricings")
public interface ProductPricingRepository extends PagingAndSortingRepository<ProductPricing,Long> {
}
