package com.awadinhoo.code.drones.services.impl;

import com.awadinhoo.code.drones.dtos.FleetRequestDTO;
import com.awadinhoo.code.drones.dtos.FleetResponseDTO;
import com.awadinhoo.code.drones.entities.Fleet;
import com.awadinhoo.code.drones.entities.Zone;
import com.awadinhoo.code.drones.enums.DeleteStatus;
import com.awadinhoo.code.drones.mappers.FleetMapper;
import com.awadinhoo.code.drones.repositories.FleetRepository;
import com.awadinhoo.code.drones.services.FleetService;
import com.awadinhoo.code.drones.services.ZoneService;
import com.awadinhoo.code.drones.validation.FleetValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FleetServiceImpl implements FleetService {

    private final FleetRepository fleetRepository;
    private final FleetValidationService fleetValidationService;
    private final FleetMapper fleetMapper;
    private final ZoneService zoneService;

    @Autowired
    public FleetServiceImpl(FleetRepository fleetRepository,
                            FleetValidationService fleetValidationService,
                            FleetMapper fleetMapper,
                            ZoneService zoneService) {
        this.fleetRepository = fleetRepository;
        this.fleetValidationService = fleetValidationService;
        this.fleetMapper = fleetMapper;
        this.zoneService = zoneService;
    }

    @Override
    public FleetResponseDTO createFleet(FleetRequestDTO fleetRequestDTO) {

        List<Zone> supportedZones = new ArrayList<>();
        List<Long> zonesIds = fleetRequestDTO.getZonesIds();

        if(zonesIds != null && !zonesIds.isEmpty()){
           supportedZones = zoneService.getNotSupportedZonesByIds(zonesIds);
           fleetValidationService.checkFleetSupportedZonesStatus(zonesIds, supportedZones);
        }

        fleetValidationService.checkFleetExistsByName(fleetRequestDTO.getName());

        Fleet fleet = fleetMapper.getFleetFromFleetRequestDTO(fleetRequestDTO);
        supportedZones.forEach( zone -> {
            zone.setFleet(fleet);
        });
        fleet.setSupportedZones(supportedZones);

        return fleetMapper.getFleetResponseDTOFromEntity(fleetRepository.save(fleet));
    }

    @Override
    public FleetResponseDTO updateFleet(Long fleetId, FleetRequestDTO fleetRequestDTO) {

        List<Zone> newSupportedZones = new ArrayList<>();
        List<Long> newZonesIds = fleetRequestDTO.getZonesIds();

        Fleet oldFleet = fleetValidationService.checkFleetAndGetIfFound(fleetId);
        fleetValidationService.checkFleetNameIsAlreadyTaken(fleetRequestDTO.getName(), fleetId);
        fleetValidationService.checkValidCapacity(oldFleet, fleetRequestDTO.getMaxNumberOfDrones());

        if(newZonesIds != null && !newZonesIds.isEmpty()){
            newSupportedZones = zoneService.getNotSupportedZonesByIdsAndOldSupportedZones(newZonesIds, fleetId);
            fleetValidationService.checkFleetSupportedZonesStatus(newZonesIds, newSupportedZones);
        }

        updateZones(oldFleet, newSupportedZones);
        updateFleetEntity(oldFleet, fleetRequestDTO, newSupportedZones);
        return fleetMapper.getFleetResponseDTOFromEntity(fleetRepository.save(oldFleet));
    }

    private void updateZones(Fleet oldFleet, List<Zone> newSupportedZones) {

        List<Zone> oldSupportedZones = oldFleet.getSupportedZones();

        List<Zone> removedZones = oldSupportedZones.stream().filter(zone -> {
            return !newSupportedZones.contains(zone);
        }).toList();

        // remove Fleet from the removed zones to be updated in the DB
        removedZones.forEach(zone -> zone.setFleet(null));
        // Add the Fleet to the new supported Zones
        newSupportedZones.forEach(zone -> zone.setFleet(oldFleet));
    }

    private void updateFleetEntity(Fleet oldFleet, FleetRequestDTO fleetRequestDTO, List<Zone> supportedZones) {
        oldFleet.setName(fleetRequestDTO.getName());
        oldFleet.setMaxNumberOfDrones(fleetRequestDTO.getMaxNumberOfDrones());
        oldFleet.setActive(fleetRequestDTO.getActive());
        oldFleet.getSupportedZones().clear();
        oldFleet.getSupportedZones().addAll(supportedZones);
    }

    @Override
    public void deleteFleet(Long fleetId) {

        Fleet oldFleet = fleetValidationService.checkFleetAndGetIfFound(fleetId);
        updateZones(oldFleet.getSupportedZones());
        oldFleet.setIsDeleted(DeleteStatus.DELETED.getStatusId());
        fleetRepository.save(oldFleet);
    }

    private void updateZones(List<Zone> supportedZones) {
        supportedZones.forEach( zone ->  zone.setFleet(null));
    }

    @Override
    public FleetResponseDTO getFleetById(Long fleetId) {

        Fleet oldFleet = fleetValidationService.checkFleetAndGetIfFound(fleetId);
        return fleetMapper.getFleetResponseDTOFromEntity(oldFleet);
    }

    @Override
    public List<FleetResponseDTO> getAllFleets() {

        List<Fleet> fleets = fleetRepository.findAllByIsDeleted(DeleteStatus.NOT_DELETED.getStatusId()).orElseGet(ArrayList::new);
        return fleetMapper.getListFleetResponseDTOFromEntities(fleets);
    }
}
