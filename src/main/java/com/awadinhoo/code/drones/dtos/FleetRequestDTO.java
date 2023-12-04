package com.awadinhoo.code.drones.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class FleetRequestDTO {

    private Long fleetId;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    private String name;
    @NotNull
    @Min(1)
    private Integer maxNumberOfDrones;
    private List<Long> zonesIds;
    private Integer active;
}
