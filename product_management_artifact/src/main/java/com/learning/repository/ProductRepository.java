package com.learning.repository;

import com.learning.entity.Product;
import com.learning.projection.InlineProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by amits on 25/09/15.
 */
@RepositoryRestResource(path = "products", collectionResourceRel = "products", excerptProjection = InlineProductDetails.class)
public interface ProductRepository extends JpaRepository<Product, Long> {
}
