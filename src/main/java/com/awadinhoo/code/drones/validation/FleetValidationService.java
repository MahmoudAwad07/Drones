package com.awadinhoo.code.drones.validation;


import com.awadinhoo.code.drones.entities.Fleet;
import com.awadinhoo.code.drones.entities.Zone;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FleetValidationService {
    void checkFleetSupportedZonesStatus(List<Long> zonesIds , List<Zone> supportedZones);
    void checkFleetExistsByName(String name);
    void checkFleetExistsById(Long fleetId);
    void checkFleetNameIsAlreadyTaken(String name , Long fleetId);
    Fleet checkFleetAndGetIfFound(Long fleetId);
    Fleet checkFleetCapacityAndGetFleet(Long fleetId);
    void checkValidCapacity(Fleet oldFleet, Integer maxNumberOfDrones);
}
