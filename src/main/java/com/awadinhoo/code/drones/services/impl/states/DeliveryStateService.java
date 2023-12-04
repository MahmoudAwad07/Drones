package com.awadinhoo.code.drones.services.impl.states;

import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Order;
import com.awadinhoo.code.drones.services.DroneStateService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryStateService implements DroneStateService {


    @Override
    public void loadOrder(Drone drone, Order order) {
        // throw new Exception
    }

    @Override
    public void deliverOrder(Drone drone, Order order) {
        // throw new Exception
    }

    @Override
    public void charge(Drone drone) throws InterruptedException {
        // throw new Exception
    }

}
