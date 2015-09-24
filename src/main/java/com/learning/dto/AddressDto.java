package com.learning.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by amits on 24/09/15.
 */
@Getter
@Setter
public class AddressDto {
    private Long addressId;
    private String locality;
    private String street;
    private String city;
    private String state;
    private String pinCode;
    private Long customerId;
}
