package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by amits on 24/09/15.
 */
@Entity
@Getter
@Setter
public class Logistic extends BaseEntity {

    private String name;

    private String email;

    @OneToMany(mappedBy = "logistic")
    private List<Shipment> shipments;
}
