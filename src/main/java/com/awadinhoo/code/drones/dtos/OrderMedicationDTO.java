package com.awadinhoo.code.drones.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderMedicationDTO {

    @NotNull
    private Long medicationId;
    @NotNull
    private Integer quantity;
}
