package com.awadinhoo.code.drones.services.impl.states;

import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Order;
import com.awadinhoo.code.drones.enums.DroneState;
import com.awadinhoo.code.drones.exceptions.DronesCustomException;
import com.awadinhoo.code.drones.repositories.DroneRepository;
import com.awadinhoo.code.drones.services.DroneStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdleStateService implements DroneStateService {

    @Autowired
    private DroneRepository droneRepository;


    @Override
    public void loadOrder(Drone drone, Order order) throws InterruptedException {
        drone.setState(DroneState.LOADING);
        droneRepository.save(drone);
        drone.setDroneStateService( new LoadingStateService());
        Thread.sleep(60000); // Loading :)
        drone.setDroneStateService( new LoadedStateService());
        drone.deliverOrder(order);
    }

    @Override
    public void deliverOrder(Drone drone, Order order) {
        throw new DronesCustomException(Constants.StatusMessages.DRONE_IS_NOT_LOADED_YET_MESSAGE);
    }

    @Override
    public void charge(Drone drone) throws InterruptedException {

        drone.setState(DroneState.CHARGING);
        droneRepository.save(drone);
        drone.setDroneStateService( new ChargingStateService());
        Thread.sleep(300000); // Charging :)
        drone.setDroneStateService(new IdleStateService());
    }
}
