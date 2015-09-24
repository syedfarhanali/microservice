package com.learning.service;

import com.learning.dto.AddressDto;
import com.learning.exception.NoSuchCustomerExistException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amits on 24/09/15.
 */
public interface AddressService {
    AddressDto addAddress(AddressDto addressDto) throws NoSuchCustomerExistException;

    @Transactional
    List<AddressDto> findAll();
}
