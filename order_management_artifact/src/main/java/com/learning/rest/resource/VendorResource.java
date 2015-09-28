package com.learning.rest.resource;

import com.learning.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by amits on 24/09/15.
 */
@Getter
@Setter
public class VendorResource {

    private String name;

    private String email;
}
