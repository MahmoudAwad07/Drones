package com.awadinhoo.code.drones.services.impl;

import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.dtos.DroneBatteryLevelDTO;
import com.awadinhoo.code.drones.dtos.DroneDTO;
import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Fleet;
import com.awadinhoo.code.drones.entities.Order;
import com.awadinhoo.code.drones.entities.Zone;
import com.awadinhoo.code.drones.enums.ActiveStatus;
import com.awadinhoo.code.drones.enums.DroneState;
import com.awadinhoo.code.drones.enums.DeleteStatus;
import com.awadinhoo.code.drones.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.drones.mappers.DroneMapper;
import com.awadinhoo.code.drones.repositories.DroneRepository;
import com.awadinhoo.code.drones.services.DroneService;
import com.awadinhoo.code.drones.validation.DroneValidationService;
import com.awadinhoo.code.drones.validation.FleetValidationService;
import com.awadinhoo.code.drones.validation.ZoneValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {
    
    private final DroneRepository droneRepository;

    private final DroneMapper droneMapper;

    private final DroneValidationService droneValidationService;

    private final FleetValidationService fleetValidationService;

    private final ZoneValidationService zoneValidationService;

    private final Predicate<Drone> availableDrones = ( drone -> {
            return drone.getState().equals(DroneState.IDLE) &&
            drone.getActive().equals(ActiveStatus.ACTIVE.getStatusId()) &&
            drone.getIsDeleted().equals(DeleteStatus.NOT_DELETED.getStatusId());});

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository, DroneMapper droneMapper,
                            DroneValidationService droneValidationService,
                            FleetValidationService fleetValidationService,
                            ZoneValidationService zoneValidationService) {
        this.droneRepository = droneRepository;
        this.droneMapper = droneMapper;
        this.droneValidationService = droneValidationService;
        this.fleetValidationService = fleetValidationService;
        this.zoneValidationService = zoneValidationService;
    }

    @Override
    public DroneDTO registerDrone(DroneDTO droneDTO) {

        Long fleetId = droneDTO.getFleetId();
        Fleet fleet = null;

        if(fleetId != null ){
            fleet = fleetValidationService.checkFleetCapacityAndGetFleet(fleetId);
        }
        droneValidationService.checkDroneExistsBySerialNumber(droneDTO.getSerialNumber());

        droneDTO.setState(DroneState.IDLE);
        Drone droneEntity = droneMapper.getDroneEntityFromDTO(droneDTO);
        droneEntity.setFleet(fleet);

        return droneMapper.getDroneDTOFromEntity(droneRepository.save(droneEntity));
    }

    @Override
    public DroneDTO updateDrone(Long droneId, DroneDTO droneDTO) {

        Long newFleetId = droneDTO.getFleetId();
        Long oldFleetId = null;
        Fleet newFleet = null;

        Drone drone = droneValidationService.checkDroneAndGetIfFound(droneId);
        droneValidationService.checkDroneSerialNumberIsAlreadyTaken(droneDTO.getSerialNumber(), droneId);

        if(drone.getFleet() != null ){
            oldFleetId = drone.getFleet().getFleetId();
        }

        updateDroneEntity(drone, droneDTO);

        if( (newFleetId != null && oldFleetId == null)){
            newFleet = fleetValidationService.checkFleetCapacityAndGetFleet(newFleetId);
            drone.setFleet(newFleet);
        }else if ((newFleetId != null && oldFleetId != null && !newFleetId.equals(oldFleetId))) {
            droneValidationService.checkDroneStatus(drone);
            newFleet = fleetValidationService.checkFleetCapacityAndGetFleet(newFleetId);
            drone.setFleet(newFleet);
        }

        return droneMapper.getDroneDTOFromEntity(droneRepository.save(drone));
    }

    private void updateDroneEntity(Drone drone, DroneDTO droneDTO) {

        drone.setSerialNumber(droneDTO.getSerialNumber());
        drone.setModel(droneDTO.getModel());
        drone.setActive(droneDTO.getActive());
        drone.setWeightLimitInGrams(droneDTO.getWeightLimitInGrams());
    }

    @Override
    public void deleteDrone(Long droneId) {

        Drone drone = droneValidationService.checkDroneAndGetIfFound(droneId);
        drone.setIsDeleted(DeleteStatus.DELETED.getStatusId());
        drone.setFleet(null);
        droneRepository.save(drone);
    }

    @Override
    public DroneDTO getDroneById(Long droneId) {

        Drone drone = droneValidationService.checkDroneAndGetIfFound(droneId);
        return droneMapper.getDroneDTOFromEntity(drone);
    }

    @Override
    public List<DroneDTO> getAllDrones() {

        List<Drone> drones = droneRepository.findAllByIsDeleted(DeleteStatus.NOT_DELETED.getStatusId()).orElseGet(ArrayList::new);
        return droneMapper.getListDroneDTOFromEntities(drones);
    }

    @Override
    public List<Drone> getAllDronesEntities() {
        return droneRepository.findAllByIsDeleted(DeleteStatus.NOT_DELETED.getStatusId()).orElseGet(ArrayList::new);
    }

    @Override
    public List<DroneDTO> getDronesByFleetId(Long fleetId) {

        List<Drone> drones = fleetValidationService.checkFleetAndGetIfFound(fleetId).getDrones().stream()
                .filter( drone -> {
                    return drone.getIsDeleted().equals(DeleteStatus.NOT_DELETED.getStatusId());
                }).collect(Collectors.toList());

        if( drones.isEmpty()){
            throw new ResourceNotFoundException(Constants.StatusMessages.FLEET_HAS_NO_DRONES_MESSAGE + fleetId);
        }
        return droneMapper.getListDroneDTOFromEntities(drones);
    }

    @Override
    public DroneBatteryLevelDTO getDroneBatterLevelById(Long droneId) {

        Drone drone = droneValidationService.checkDroneAndGetIfFound(droneId);
        return droneMapper.getDroneBatteryLevelDTOFromEntity(drone);
    }

    @Override
    public List<DroneDTO> getAvailableDronesToDeliverByFleetId(Long fleetId) {

        List<Drone> drones = fleetValidationService.checkFleetAndGetIfFound(fleetId).getDrones().stream()
                .filter(availableDrones).collect(Collectors.toList());

        if( drones.isEmpty()){
            throw new ResourceNotFoundException(Constants.StatusMessages.FLEET_HAS_NO_DRONES_TO_DELIVER_MESSAGE + fleetId);
        }

        return droneMapper.getListDroneDTOFromEntities(drones);
    }

    @Override
    public List<DroneDTO> getAvailableDronesToDeliverByZoneId(Long zoneId) {

        Zone zone = zoneValidationService.checkZoneAndGetIfFound(zoneId);
        Fleet fleet = zone.getFleet();

        if(fleet == null){
            throw new ResourceNotFoundException(Constants.StatusMessages.ZONE_IS_NOT_SERVED_BY_ANY_FLEETS_MESSAGE + zoneId);
        }

        List<Drone> drones = fleet.getDrones().stream()
                .filter(availableDrones).collect(Collectors.toList());

        if( drones.isEmpty()){
            throw new ResourceNotFoundException(Constants.StatusMessages.Zone_HAS_NO_DRONES_TO_DELIVER_MESSAGE + zoneId);
        }

        return droneMapper.getListDroneDTOFromEntities(drones);
    }

    @Override
    public void loadDrone(Drone drone, Order order) {

        drone.setState(DroneState.LOADING);
        drone.getOrders().add(order);
        order.setDrone(drone);
        droneRepository.save(drone);
    }

    @Override
    public void saveDroneList(List<Drone> drones) {
        droneRepository.saveAll(drones);
    }

}
