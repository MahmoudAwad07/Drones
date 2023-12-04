package com.awadinhoo.code.drones.services;


import com.awadinhoo.code.drones.dtos.ZoneDTO;
import com.awadinhoo.code.drones.entities.Zone;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ZoneService {
    ZoneDTO createZone(ZoneDTO zoneDTO);
    ZoneDTO updateZone(Long zoneId, ZoneDTO updatedZoneDTO);
    void deleteZone(Long zoneId);
    ZoneDTO getZoneById(Long zoneId);
    List<ZoneDTO> getAllZones();
    List<Zone> getZonesByIds(List<Long> zonesIds);
    List<Zone> getNotSupportedZonesByIds(List<Long> zonesIds);
    List<Zone> getNotSupportedZonesByIdsAndOldSupportedZones(List<Long> zonesIds, Long fleetId);
}
