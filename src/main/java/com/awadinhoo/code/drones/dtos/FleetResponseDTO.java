package com.awadinhoo.code.drones.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class FleetResponseDTO {
    private Long fleetId;
    private String name;
    private Integer maxNumberOfDrones;
    private List<ZoneDTO> supportedZones;
    private List<DroneDTO> drones;
    private Integer active;
}
