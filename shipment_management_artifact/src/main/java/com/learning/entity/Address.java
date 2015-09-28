package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by amits on 22/09/15.
 */
@Entity
@Getter
@Setter
public class Address extends BaseEntity {
    private String locality;
    private String city;
    private String state;
    private String pinCode;
}
