package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by amits on 27/09/15.
 */
@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    private String name;

    private String description;
}
