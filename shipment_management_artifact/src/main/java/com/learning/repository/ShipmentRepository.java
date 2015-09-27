package com.learning.repository;

import com.learning.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amits on 24/09/15.
 */
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
}
