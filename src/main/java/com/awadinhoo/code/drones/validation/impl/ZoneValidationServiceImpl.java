package com.awadinhoo.code.drones.validation.impl;

import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.entities.Zone;
import com.awadinhoo.code.drones.enums.ActiveStatus;
import com.awadinhoo.code.drones.enums.DeleteStatus;
import com.awadinhoo.code.drones.exceptions.DronesCustomException;
import com.awadinhoo.code.drones.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.drones.repositories.ZoneRepository;
import com.awadinhoo.code.drones.validation.ZoneValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ZoneValidationServiceImpl implements ZoneValidationService {

    private final ZoneRepository zoneRepository;

    @Autowired
    public ZoneValidationServiceImpl (ZoneRepository zoneRepository){
        this.zoneRepository = zoneRepository;
    }

    @Override
    public void checkZoneExistsByName(String name) {
        boolean zoneExists = zoneRepository.findByNameAndIsDeleted(name, DeleteStatus.NOT_DELETED.getStatusId()).isPresent();

        if(zoneExists){
            throw new DronesCustomException(Constants.StatusMessages.ZONE_IS_FOUND_WITH_THE_SAME_NAME_MESSAGE + name);
        }
    }

    @Override
    public void checkZoneExistsById(Long id) {
        boolean zoneExists = zoneRepository.findByZoneIdAndIsDeleted(id, DeleteStatus.NOT_DELETED.getStatusId()).isPresent();

        if(!zoneExists){
            throw new ResourceNotFoundException(Constants.StatusMessages.ZONE_NOT_FOUND_MESSAGE + id);
        }
    }

    @Override
    public void checkZoneNameIsAlreadyTaken(String name, Long zoneId) {
        Optional<Zone> optionalZone = zoneRepository.findByNameAndIsDeleted(name, DeleteStatus.NOT_DELETED.getStatusId());

        if(optionalZone.isPresent() && !optionalZone.get().getZoneId().equals(zoneId)){
            throw new DronesCustomException(Constants.StatusMessages.ZONE_IS_FOUND_WITH_THE_SAME_NAME_MESSAGE + name);
        }
    }

    @Override
    public Zone checkZoneAndGetIfFound(Long id) {

        Optional<Zone> zoneOptional = zoneRepository.findByZoneIdAndIsDeleted(id, DeleteStatus.NOT_DELETED.getStatusId());

        if(zoneOptional.isEmpty()){
            throw new ResourceNotFoundException(Constants.StatusMessages.ZONE_NOT_FOUND_MESSAGE + id);
        }

        return zoneOptional.get();
    }

    @Override
    public void checkZoneIsActive(Zone zone) {

        if(zone.getActive().equals(ActiveStatus.NOT_ACTIVE.getStatusId())){
            throw new DronesCustomException(Constants.StatusMessages.ZONE_MUST_BE_IN_ACTIVE_STATUS_MESSAGE);
        }
    }
}
