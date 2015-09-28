package com.learning.listener;

import com.learning.event.Event;
import com.learning.handler.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by amits on 28/09/15.
 */
@Component("shipmentEventHandler")
public class ShipmentEventHandler implements EventHandler {

    @Override
    public void handleEvent(Event event) {
        System.out.println(event);
    }
}
