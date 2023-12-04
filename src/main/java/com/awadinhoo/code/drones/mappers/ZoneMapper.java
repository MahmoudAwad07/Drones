package com.awadinhoo.code.drones.mappers;


import com.awadinhoo.code.drones.dtos.ZoneDTO;
import com.awadinhoo.code.drones.entities.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ZoneMapper {

    ZoneDTO getZoneDTOFromEntity(Zone zoneEntity);
    Zone getZoneEntityFromDTO(ZoneDTO zoneDTO);
    List<ZoneDTO> getListZoneDTOFromEntities(List<Zone> ZoneEntityList);
    List<Zone> getListZoneEntityFromDTOs(List<ZoneDTO> zoneDTOList);

}
