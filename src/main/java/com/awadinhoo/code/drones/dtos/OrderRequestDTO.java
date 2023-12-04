package com.awadinhoo.code.drones.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class OrderRequestDTO {

    @NotBlank
    private String address;
    @NotNull
    private Long zoneId;
    @NotNull
    @Valid
    private List<OrderMedicationDTO> orderMedications;

}
