package com.learning.controller;

import com.learning.dto.AddressDto;
import com.learning.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by amits on 24/09/15.
 */
@RequestMapping("/address")
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping("/findAll")
    public List<AddressDto> findAll() {
        return addressService.findAll();
    }
}
