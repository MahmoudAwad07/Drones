package com.awadinhoo.code.drones.services.impl.states;


import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Order;
import com.awadinhoo.code.drones.exceptions.DronesCustomException;
import com.awadinhoo.code.drones.services.DroneStateService;
import org.springframework.stereotype.Service;

@Service
public class ChargingStateService implements DroneStateService {

    @Override
    public void loadOrder(Drone drone, Order order)  {
        throw new DronesCustomException(Constants.StatusMessages.DRONE_CAN_NOT_BE_LOADED_WHILE_CHARGING_MESSAGE);
    }

    @Override
    public void deliverOrder(Drone drone, Order order) {
        throw new DronesCustomException(Constants.StatusMessages.DRONE_CAN_NOT_DELIVER_WHILE_CHARGING_MESSAGE);
    }

    @Override
    public void charge(Drone drone) throws InterruptedException {
        throw new DronesCustomException(Constants.StatusMessages.DRONE_IS_ALREADY_CHARGING_MESSAGE);
    }


}
