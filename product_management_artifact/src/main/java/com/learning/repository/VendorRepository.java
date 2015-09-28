package com.learning.repository;

import com.learning.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by amits on 26/09/15.
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findByName(String vendorName);
}
