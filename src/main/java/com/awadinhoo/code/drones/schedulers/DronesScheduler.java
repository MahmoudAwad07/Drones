package com.awadinhoo.code.drones.schedulers;

import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.DroneAudit;
import com.awadinhoo.code.drones.enums.DroneState;
import com.awadinhoo.code.drones.services.DroneAuditService;
import com.awadinhoo.code.drones.services.DroneService;
import com.awadinhoo.code.drones.services.impl.states.ChargingStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class DronesScheduler {


    private final DroneService droneService;

    private final DroneAuditService droneAuditService;

    @Autowired
    public DronesScheduler(DroneService droneService,
                           DroneAuditService droneAuditService) {
        this.droneService = droneService;
        this.droneAuditService = droneAuditService;
    }

    @Scheduled(fixedRate = 60000) // Execute every 60 seconds
    public void checkDronesBattery() {

        List<Drone> drones = droneService.getAllDronesEntities();
        List<Drone> dronesToRecharge = new ArrayList<>();

        List<DroneAudit> droneAudits = drones.stream().map(drone -> {

            if(drone.getState().equals(DroneState.IDLE) && drone.getBatteryCapacity() < 25){
                drone.setDroneStateService(new ChargingStateService());
                drone.setState(DroneState.CHARGING);
                dronesToRecharge.add(drone);
            }
            DroneAudit droneAudit = new DroneAudit();
            droneAudit.setDrone(drone);
            droneAudit.setBatteryLevel(drone.getBatteryCapacity());
            droneAudit.setAuditTime(LocalDateTime.now());
            return droneAudit;
        }).toList();

        droneService.saveDroneList(dronesToRecharge);
        droneAuditService.saveDroneAudits(droneAudits);
    }

}
