package com.learning.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amits on 24/09/15.
 */
@Getter
@Setter
public class CustomerDto {

    private Long customerId;

    private String name;

    private String email;

    List<AddressDto> addresses = new ArrayList<>();
}
