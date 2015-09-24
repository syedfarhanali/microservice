package com.learning.repository;

import com.learning.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by amits on 22/09/15.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCustomerId(Long customerId);

    Address findByIdAndCustomerId(Long addressId, Long customerId);

}
