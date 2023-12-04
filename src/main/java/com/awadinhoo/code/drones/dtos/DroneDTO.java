package com.awadinhoo.code.drones.dtos;

import com.awadinhoo.code.drones.enums.DroneModel;
import com.awadinhoo.code.drones.enums.DroneState;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DroneDTO {

    private Long droneId;
    @NotBlank
    @Size(max = 100)
    private String serialNumber;
    @NotNull
    private DroneModel model;
    private DroneState state;
    @NotNull
    @Min(1)
    private Long weightLimitInGrams;
    @NotNull
    @Min(1)
    @Max(100)
    private Integer batteryCapacity;
    private Long fleetId;
    private Integer active;

}
