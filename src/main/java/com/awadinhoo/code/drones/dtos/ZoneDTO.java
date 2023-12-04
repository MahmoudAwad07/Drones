package com.awadinhoo.code.drones.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ZoneDTO {

    private Long zoneId;
    @NotBlank
    private String name;
    @NotNull
    @Min(5)
    private Integer maxTimeMinutesForLightWeightDrone;
    @NotNull
    @Min(5)
    private Integer maxTimeMinutesForMiddleWeightDrone;
    @NotNull
    @Min(5)
    private Integer maxTimeMinutesForCruiseWeightDrone;
    @NotNull
    @Min(5)
    private Integer maxTimeMinutesForHeavyWeightDrone;
    @NotNull
    @Min(1)
    @Max(99)
    private Integer maxBatteryCapacityConsumedPerTrip;
    private Integer active;

}
