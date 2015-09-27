package com.learning.repository;

import com.learning.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amits on 22/09/15.
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByProductName(String productName);

    Inventory removeByProductName(String productName);

    Inventory removeByProductId(Long productId);

    Inventory findByProductId(Long productId);
}
