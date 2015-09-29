package com.learning.service.impl;

import com.learning.entity.Shipment;
import com.learning.repository.ShipmentRepository;
import com.learning.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ArindamN on 29/09/2015.
 */
@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public Shipment createShipment(Shipment shipment) {
        return  shipmentRepository.save(shipment);
    }
}
