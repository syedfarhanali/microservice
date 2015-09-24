package com.learning.service.impl;

import com.learning.dto.AddressDto;
import com.learning.entity.Address;
import com.learning.entity.Customer;
import com.learning.exception.NoSuchCustomerExistException;
import com.learning.repository.AddressRepository;
import com.learning.repository.CustomerRepository;
import com.learning.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amits on 24/09/15.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AddressDto addAddress(AddressDto addressDto) throws NoSuchCustomerExistException {
        Customer customer = customerRepository.findOne(addressDto.getCustomerId());
        if (customer == null) {
            throw new NoSuchCustomerExistException("No customer found for id :" + addressDto.getCustomerId());
        }
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setLocality(addressDto.getLocality());
        address.setPinCode(addressDto.getPinCode());
        address.setState(address.getState());
        address.setCustomer(customer);
        address = addressRepository.save(address);
        addressDto.setAddressId(address.getId());
        return addressDto;
    }

    @Transactional
    @Override
    public List<AddressDto> findAll() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream().map(this::addressToAddressDto).collect(Collectors.toList());
    }

    private AddressDto addressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(address.getId());
        addressDto.setState(address.getState());
        addressDto.setPinCode(address.getPinCode());
        addressDto.setCity(address.getCity());
        addressDto.setCustomerId(address.getCustomer().getId());

        return addressDto;
    }
}
