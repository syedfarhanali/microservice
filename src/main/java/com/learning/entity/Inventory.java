package com.learning.entity;

import com.learning.exception.InsufficientItemStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by amits on 22/09/15.
 */
@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue
    private Long inventoryId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer totalItems;

    public void addItems(Integer itemCount) {
        totalItems += itemCount;
    }

    public void removeItems(Integer itemCount) throws InsufficientItemStockException {
        totalItems -= itemCount;
    }
}
