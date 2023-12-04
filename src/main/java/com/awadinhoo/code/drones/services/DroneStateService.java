package com.awadinhoo.code.drones.services;

import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Order;

public interface DroneStateService {

    void loadOrder(Drone drone, Order order) throws InterruptedException;
    void deliverOrder(Drone drone, Order order) throws InterruptedException;
    void charge(Drone drone) throws InterruptedException;
}
