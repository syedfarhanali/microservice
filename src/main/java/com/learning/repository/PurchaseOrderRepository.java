package com.learning.repository;

import com.learning.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amits on 22/09/15.
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
