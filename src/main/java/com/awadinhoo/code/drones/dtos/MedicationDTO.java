package com.awadinhoo.code.drones.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MedicationDTO {

    private Long medicationId;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    private String name;
    @NotNull
    @Pattern(regexp = "^[A-Z0-9_-]+$")
    private String code;
    @NotNull
    @Min(1)
    private Integer weightInGrams;
    private Integer quantity;
    private byte[] image;
    private String imageFileName;
    private Integer active;
}
