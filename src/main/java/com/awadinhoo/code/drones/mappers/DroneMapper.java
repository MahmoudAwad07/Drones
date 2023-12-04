package com.awadinhoo.code.drones.mappers;

import com.awadinhoo.code.drones.dtos.DroneBatteryLevelDTO;
import com.awadinhoo.code.drones.dtos.DroneDTO;
import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Fleet;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DroneMapper {

    @Mapping(target = "fleetId", source = "fleet", qualifiedByName = "getFleetId")
    DroneDTO getDroneDTOFromEntity(Drone droneEntity);
    Drone getDroneEntityFromDTO(DroneDTO droneDTO);
    List<DroneDTO> getListDroneDTOFromEntities(List<Drone> droneList);
    List<Drone> getListDroneEntityFromDTOs(List<DroneDTO> droneDTOList);
    DroneBatteryLevelDTO getDroneBatteryLevelDTOFromEntity(Drone droneEntity);
    @Named("getFleetId")
    default Long getFleetId(Fleet fleet) {
        return fleet.getFleetId();
    }

}
