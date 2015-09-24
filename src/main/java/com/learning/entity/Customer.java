package com.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by amits on 22/09/15.
 */
@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue
    private Long customerId;

    private String name;

    private String email;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Address> addressList;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoiceList;
    @OneToMany(mappedBy = "customer")
    private List<PurchaseOrder> purchaseOrderList;
}
