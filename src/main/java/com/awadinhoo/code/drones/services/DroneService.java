package com.awadinhoo.code.drones.services;


import com.awadinhoo.code.drones.dtos.DroneBatteryLevelDTO;
import com.awadinhoo.code.drones.dtos.DroneDTO;
import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Order;

import java.util.List;

public interface DroneService {
    DroneDTO registerDrone(DroneDTO droneDTO);
    DroneDTO updateDrone(Long droneId, DroneDTO droneDTO);
    void deleteDrone(Long droneId);
    DroneDTO getDroneById(Long droneId);
    List<DroneDTO> getAllDrones();
    List<Drone> getAllDronesEntities();
    List<DroneDTO> getDronesByFleetId(Long fleetId);
    DroneBatteryLevelDTO getDroneBatterLevelById(Long droneId);
    List<DroneDTO> getAvailableDronesToDeliverByFleetId(Long fleetId);
    List<DroneDTO> getAvailableDronesToDeliverByZoneId(Long zoneId);
    void loadDrone(Drone drone, Order order);
    void saveDroneList(List<Drone> drones);
}
