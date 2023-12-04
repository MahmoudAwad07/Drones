package com.awadinhoo.code.drones.validation;


import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Fleet;
import com.awadinhoo.code.drones.entities.Zone;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DroneValidationService {
    void checkDroneExistsBySerialNumber(String serialNumber);
    void checkDroneExistsById(Long droneId);
    void checkDroneSerialNumberIsAlreadyTaken(String serialNumber , Long droneId);
    Drone checkDroneAndGetIfFound(Long droneId);
    void checkDroneStatus(Drone drone);
    void checkDroneIsActive(Drone drone);
}
