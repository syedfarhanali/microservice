package com.learning.service;

import com.learning.dto.CustomerDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amits on 24/09/15.
 */
public interface CustomerService {

    CustomerDto addCustomer(CustomerDto customerDto);

    void removeCustomer(CustomerDto customerDto);

    @Transactional
    List<CustomerDto> findAll();
}
