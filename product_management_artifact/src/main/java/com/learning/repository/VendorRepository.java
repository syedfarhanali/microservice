package com.learning.repository;

import com.learning.entity.Vendor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by amits on 26/09/15.
 */
@RepositoryRestResource(path = "vendors", collectionResourceRel = "vendors")
public interface VendorRepository extends PagingAndSortingRepository<Vendor,Long> {
}
