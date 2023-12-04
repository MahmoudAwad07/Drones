package com.awadinhoo.code.drones.validation;


import com.awadinhoo.code.drones.entities.Zone;
import org.springframework.stereotype.Service;

public interface ZoneValidationService {
    void checkZoneExistsByName(String name);
    void checkZoneExistsById(Long id);
    void checkZoneNameIsAlreadyTaken(String name , Long id);
    Zone checkZoneAndGetIfFound(Long id);
    void checkZoneIsActive(Zone zone);
}
