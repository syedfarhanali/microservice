package com.learning.repository;

import com.learning.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amits on 22/09/15.
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByProductProductName(String productName);

    Inventory removeByProductProductName(String productName);

    Inventory removeByProductProductId(Long productId);

    Inventory findByProductProductId(Long productId);
}
