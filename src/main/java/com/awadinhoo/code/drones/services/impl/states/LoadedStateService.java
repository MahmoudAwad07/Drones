package com.awadinhoo.code.drones.services.impl.states;

import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Order;
import com.awadinhoo.code.drones.enums.DroneState;
import com.awadinhoo.code.drones.exceptions.DronesCustomException;
import com.awadinhoo.code.drones.repositories.DroneRepository;
import com.awadinhoo.code.drones.services.DroneStateService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoadedStateService implements DroneStateService {

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public void loadOrder(Drone drone, Order order) {
        throw new DronesCustomException(Constants.StatusMessages.DRONE_IS_ALREADY_LOADED_MESSAGE);
    }

    @Override
    public void deliverOrder(Drone drone, Order order) throws InterruptedException {
        drone.setDroneStateService(new DeliveryStateService());
        drone.setState(DroneState.DELIVERING);
        droneRepository.save(drone);
        Thread.sleep(order.getZone().getMaxTimeMinutesForLightWeightDrone() * 1000);
        drone.setDroneStateService(new IdleStateService());
    }

    @Override
    public void charge(Drone drone) throws InterruptedException {
        throw new DronesCustomException(Constants.StatusMessages.DRONE_CAN_NOT_CHARGE_AFTER_LOADED);
    }


}
