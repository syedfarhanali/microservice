package com.learning.repository;

import com.learning.entity.ProductPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amits on 26/09/15.
 */
public interface ProductPricingRepository extends JpaRepository<ProductPricing, Long> {
}
