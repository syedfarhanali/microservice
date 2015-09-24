package com.learning.repository;

import com.learning.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amits on 22/09/15.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
