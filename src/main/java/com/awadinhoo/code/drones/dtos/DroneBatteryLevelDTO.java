package com.awadinhoo.code.drones.dtos;


import lombok.Data;

@Data
public class DroneBatteryLevelDTO {
    private Long droneId;
    private Integer batteryCapacity;
}
