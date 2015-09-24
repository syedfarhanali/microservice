package com.learning.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by amits on 23/09/15.
 */
@Getter
@Setter
public class InventoryDto {

    private Long inventoryId;
    private Long productId;
    private String productName;
    private int totalItems;
}
