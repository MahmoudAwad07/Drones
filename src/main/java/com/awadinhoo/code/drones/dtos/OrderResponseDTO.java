package com.awadinhoo.code.drones.dtos;

import com.awadinhoo.code.drones.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderResponseDTO {

    private Long orderId;
    private String orderNumber;
    private OrderStatus orderStatus;
    private Integer estimatedDeliveryTimeInMinutes;
}
