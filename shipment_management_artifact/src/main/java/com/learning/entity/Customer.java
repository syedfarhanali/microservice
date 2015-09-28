package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by amits on 22/09/15.
 */
@Entity
@Getter
@Setter
public class Customer extends BaseEntity {

    private String name;

    private String email;

    @OneToMany(mappedBy = "orderCustomer", cascade = CascadeType.ALL)
    private List<Address> addresses;
}
