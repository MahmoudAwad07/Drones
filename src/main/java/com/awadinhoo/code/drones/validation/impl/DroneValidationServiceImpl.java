package com.awadinhoo.code.drones.validation.impl;

import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.enums.ActiveStatus;
import com.awadinhoo.code.drones.enums.DeleteStatus;
import com.awadinhoo.code.drones.enums.DroneState;
import com.awadinhoo.code.drones.exceptions.DronesCustomException;
import com.awadinhoo.code.drones.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.drones.repositories.DroneRepository;
import com.awadinhoo.code.drones.validation.DroneValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneValidationServiceImpl implements DroneValidationService {

    private final DroneRepository droneRepository;

    @Autowired
    public DroneValidationServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public void checkDroneExistsBySerialNumber(String serialNumber) {

        boolean droneExists = droneRepository.findBySerialNumberAndIsDeleted(serialNumber, DeleteStatus.NOT_DELETED.getStatusId()).isPresent();

        if(droneExists){
            throw new DronesCustomException(Constants.StatusMessages.DRONE_IS_FOUND_WITH_THE_SAME_SERIAL_NUMBER_MESSAGE + serialNumber);
        }
    }

    @Override
    public void checkDroneExistsById(Long droneId) {

        boolean droneExists = droneRepository.findByDroneIdAndIsDeleted(droneId, DeleteStatus.NOT_DELETED.getStatusId()).isPresent();

        if(!droneExists){
            throw new ResourceNotFoundException(Constants.StatusMessages.DRONE_IS_NOT_FOUND_MESSAGE + droneId);
        }

    }

    @Override
    public void checkDroneSerialNumberIsAlreadyTaken(String serialNumber, Long droneId) {

        Optional<Drone> optionalDrone = droneRepository.findBySerialNumberAndIsDeleted(serialNumber, DeleteStatus.NOT_DELETED.getStatusId());

        if(optionalDrone.isPresent() && !optionalDrone.get().getDroneId().equals(droneId)){
            throw new DronesCustomException(Constants.StatusMessages.DRONE_IS_ALREADY_EXIST_WITH_SERIALNUMBER_MESSAGE + serialNumber);
        }
    }

    @Override
    public Drone checkDroneAndGetIfFound(Long droneId) {

        Optional<Drone> optionalDrone = droneRepository.findByDroneIdAndIsDeleted(droneId, DeleteStatus.NOT_DELETED.getStatusId());

        if(optionalDrone.isEmpty()){
            throw new ResourceNotFoundException(Constants.StatusMessages.DRONE_IS_NOT_FOUND_MESSAGE + droneId);
        }

        return optionalDrone.get();
    }

    @Override
    public void checkDroneStatus(Drone drone) {

        if(!drone.getState().equals(DroneState.IDLE) && !drone.getState().equals(DroneState.DELIVERED) ){
            throw new DronesCustomException(Constants.StatusMessages.DRONE_MUST_BE_NOT_DELIVERING_TO_BE_ASSIGNED_TO_ANOTHER_FLEET_MESSAGE);
        }
    }

    @Override
    public void checkDroneIsActive(Drone drone) {

        if(drone.getActive().equals(ActiveStatus.NOT_ACTIVE.getStatusId())){
            throw new DronesCustomException(Constants.StatusMessages.DRONE_MUST_BE_IN_ACTIVE_STATUS_MESSAGE);
        }
    }
}
