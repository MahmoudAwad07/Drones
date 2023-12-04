package com.awadinhoo.code.drones.validation.impl;

import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.entities.Fleet;
import com.awadinhoo.code.drones.entities.Zone;
import com.awadinhoo.code.drones.enums.ActiveStatus;
import com.awadinhoo.code.drones.enums.DeleteStatus;
import com.awadinhoo.code.drones.exceptions.DronesCustomException;
import com.awadinhoo.code.drones.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.drones.repositories.FleetRepository;
import com.awadinhoo.code.drones.validation.FleetValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FleetValidationServiceImpl implements FleetValidationService {

    private final FleetRepository fleetRepository;

    @Autowired
    public FleetValidationServiceImpl(FleetRepository fleetRepository) {
        this.fleetRepository = fleetRepository;
    }

    @Override
    public void checkFleetSupportedZonesStatus(List<Long> zonesIds, List<Zone> supportedZones) {

        if(zonesIds.size() != supportedZones.size()){
            throw new DronesCustomException(Constants.StatusMessages.FLEET_SUPPORTED_ZONES_INVALID_STATUS_MESSAGE);
        }
    }

    @Override
    public void checkFleetExistsByName(String fleetName) {

        boolean fleetExists = fleetRepository.findByNameAndIsDeleted(fleetName, DeleteStatus.NOT_DELETED.getStatusId()).isPresent();

        if(fleetExists){
            throw new DronesCustomException(Constants.StatusMessages.FLEET_IS_FOUND_WITH_THE_SAME_NAME_MESSAGE + fleetName);
        }
    }

    @Override
    public void checkFleetExistsById(Long fleetId) {

        boolean fleetExists = fleetRepository.findByFleetIdAndIsDeleted(fleetId, DeleteStatus.NOT_DELETED.getStatusId()).isPresent();

        if(!fleetExists){
            throw new ResourceNotFoundException(Constants.StatusMessages.FLEET_NOT_FOUND_MESSAGE + fleetId);
        }
    }

    @Override
    public void checkFleetNameIsAlreadyTaken(String fleetName, Long fleetId) {

        Optional<Fleet> optionalFleet = fleetRepository.findByNameAndIsDeleted(fleetName, DeleteStatus.NOT_DELETED.getStatusId());

        if(optionalFleet.isPresent() && !optionalFleet.get().getFleetId().equals(fleetId)){
            throw new DronesCustomException(Constants.StatusMessages.FLEET_IS_FOUND_WITH_THE_SAME_NAME_MESSAGE + fleetName);
        }
    }

    @Override
    public Fleet checkFleetAndGetIfFound(Long fleetId) {

        Optional<Fleet> fleetOptional = fleetRepository.findByFleetIdAndIsDeleted(fleetId, DeleteStatus.NOT_DELETED.getStatusId());

        if(fleetOptional.isEmpty()){
            throw new ResourceNotFoundException(Constants.StatusMessages.FLEET_NOT_FOUND_MESSAGE + fleetId);
        }

        return fleetOptional.get();
    }


    @Override
    public Fleet checkFleetCapacityAndGetFleet(Long fleetId) {

        Fleet fleet = checkFleetAndGetIfFound(fleetId);

        if(fleet.getActive().equals(ActiveStatus.NOT_ACTIVE.getStatusId())){
            throw new DronesCustomException(Constants.StatusMessages.FLEET_MUST_BE_IN_ACTIVE_STATE_MESSAGE+ fleetId);
        }

        if(fleet.getDrones().size() + 1 > fleet.getMaxNumberOfDrones()){
            throw new DronesCustomException(Constants.StatusMessages.NUMBER_OF_DRONES_EXCEEDS_FLEET_LIMIT_MESSAGE);
        }

        return fleet;
    }

    @Override
    public void checkValidCapacity(Fleet oldFleet, Integer newMaxNumberOfDrones) {

        int fleetCapacity = oldFleet.getDrones().size();

        if(fleetCapacity > newMaxNumberOfDrones){
            throw new DronesCustomException(Constants.StatusMessages.INVALID_NEW_FLEET_CAPACITY_MESSAGE + fleetCapacity);
        }
    }

}
