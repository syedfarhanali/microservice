package com.learning.service.impl;

import com.learning.dto.CustomerDto;
import com.learning.entity.Customer;
import com.learning.repository.CustomerRepository;
import com.learning.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amits on 24/09/15.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {

        return null;
    }

    @Override
    public void removeCustomer(CustomerDto customerDto) {

    }

    @Transactional
    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(this::customerToCustomerDto).collect(Collectors.toList());
    }

    private CustomerDto customerToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getId());
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());

        return customerDto;
    }
}
