package com.learning.repository;

import com.learning.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by amits on 22/09/15.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCustomerCustomerId(Long customerId);

    Address findByAddressIdAndCustomerCustomerId(Long addressId, Long customerId);

}
