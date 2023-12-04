package com.awadinhoo.code.drones.services.impl;

import com.awadinhoo.code.drones.dtos.ZoneDTO;
import com.awadinhoo.code.drones.entities.Zone;
import com.awadinhoo.code.drones.enums.ActiveStatus;
import com.awadinhoo.code.drones.enums.DeleteStatus;
import com.awadinhoo.code.drones.mappers.ZoneMapper;
import com.awadinhoo.code.drones.repositories.ZoneRepository;
import com.awadinhoo.code.drones.services.ZoneService;
import com.awadinhoo.code.drones.validation.ZoneValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    private final ZoneValidationService zoneValidationService;

    private final ZoneMapper zoneMapper;

    @Autowired
    public ZoneServiceImpl(ZoneRepository zoneRepository,
                           ZoneValidationService zoneValidationService,
                           ZoneMapper zoneMapper) {
        this.zoneRepository = zoneRepository;
        this.zoneValidationService = zoneValidationService;
        this.zoneMapper = zoneMapper;
    }

    @Override
    public ZoneDTO createZone(ZoneDTO zoneDTO) {

        zoneValidationService.checkZoneExistsByName(zoneDTO.getName());
        Zone savedZone = zoneRepository.save(zoneMapper.getZoneEntityFromDTO(zoneDTO));
        return zoneMapper.getZoneDTOFromEntity(savedZone);
    }

    @Override
    public ZoneDTO updateZone(Long zoneId, ZoneDTO updatedZoneDTO) {

        zoneValidationService.checkZoneExistsById(zoneId);
        zoneValidationService.checkZoneNameIsAlreadyTaken(updatedZoneDTO.getName(), updatedZoneDTO.getZoneId());
        Zone updatedZone = zoneRepository.save(zoneMapper.getZoneEntityFromDTO(updatedZoneDTO));
        return zoneMapper.getZoneDTOFromEntity(updatedZone);

    }

    @Override
    public void deleteZone(Long zoneId) {
        Zone deletedZone = zoneValidationService.checkZoneAndGetIfFound(zoneId);
        deletedZone.setIsDeleted(DeleteStatus.DELETED.getStatusId());
        zoneRepository.save(deletedZone);
    }

    @Override
    public ZoneDTO getZoneById(Long zoneId) {
        Zone zone = zoneValidationService.checkZoneAndGetIfFound(zoneId);
        return zoneMapper.getZoneDTOFromEntity(zone);
    }

    @Override
    public List<ZoneDTO> getAllZones() {
        List<Zone> allZones = zoneRepository.findAllByIsDeleted(DeleteStatus.NOT_DELETED.getStatusId()).orElseGet(ArrayList::new);
        return zoneMapper.getListZoneDTOFromEntities(allZones);
    }

    @Override
    public List<Zone> getZonesByIds(List<Long> zonesIds) {
        return zoneRepository.findAllByZoneIdInAndIsDeleted(zonesIds, DeleteStatus.NOT_DELETED.getStatusId()).orElseGet(ArrayList::new);
    }

    @Override
    public List<Zone> getNotSupportedZonesByIds(List<Long> zonesIds) {
        return zoneRepository.findAllByZoneIdAndIsDeletedAndIsActive(zonesIds, ActiveStatus.ACTIVE.getStatusId() , DeleteStatus.NOT_DELETED.getStatusId()).orElseGet(ArrayList::new);
    }

    @Override
    public List<Zone> getNotSupportedZonesByIdsAndOldSupportedZones(List<Long> zonesIds, Long fleetId) {
        return zoneRepository.findAllByZoneIdAndIsDeletedAndIsActiveAndFleetId(zonesIds, ActiveStatus.ACTIVE.getStatusId(),
                DeleteStatus.NOT_DELETED.getStatusId(), fleetId).orElseGet(ArrayList::new);
    }
}
