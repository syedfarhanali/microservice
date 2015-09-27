package com.learning.repository;

import com.learning.entity.ProductDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by amits on 26/09/15.
 */
@RepositoryRestResource(path = "productDetails", collectionResourceRel = "productDetails")
public interface ProductDetailRepository extends PagingAndSortingRepository<ProductDetail,Long> {
}
