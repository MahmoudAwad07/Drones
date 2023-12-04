package com.awadinhoo.code.drones.mappers;

import com.awadinhoo.code.drones.dtos.FleetRequestDTO;
import com.awadinhoo.code.drones.dtos.FleetResponseDTO;
import com.awadinhoo.code.drones.entities.Fleet;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FleetMapper {

    FleetResponseDTO getFleetResponseDTOFromFleetRequestDTO(FleetRequestDTO fleetRequestDTO);
    Fleet getFleetFromFleetRequestDTO(FleetRequestDTO fleetRequestDTO);
    FleetResponseDTO getFleetResponseDTOFromEntity(Fleet fleet);
    Fleet getFleetEntityFromDTO(FleetResponseDTO fleetResponseDTO);
    List<FleetResponseDTO> getListFleetResponseDTOFromEntities(List<Fleet> fleetList);
    List<Fleet> getListFleetEntityFromDTOs(List<FleetResponseDTO> fleetResponseDTOS);
}
