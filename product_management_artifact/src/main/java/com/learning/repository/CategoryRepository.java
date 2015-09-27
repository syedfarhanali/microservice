package com.learning.repository;

import com.learning.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by amits on 26/09/15.
 */
@RepositoryRestResource(path = "categories", collectionResourceRel = "categories")
public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {
}
