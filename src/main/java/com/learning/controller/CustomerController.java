package com.learning.controller;

import com.learning.dto.CustomerDto;
import com.learning.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by amits on 24/09/15.
 */
@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/findAll")
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }
}
