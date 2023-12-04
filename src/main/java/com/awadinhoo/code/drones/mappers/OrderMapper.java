package com.awadinhoo.code.drones.mappers;


import com.awadinhoo.code.drones.dtos.FleetRequestDTO;
import com.awadinhoo.code.drones.dtos.FleetResponseDTO;
import com.awadinhoo.code.drones.dtos.OrderRequestDTO;
import com.awadinhoo.code.drones.dtos.OrderResponseDTO;
import com.awadinhoo.code.drones.entities.Fleet;
import com.awadinhoo.code.drones.entities.Order;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper {


    Order getOrderFromOrderRequestDTO(OrderRequestDTO orderRequestDTO);

    @Mapping(target = "orderNumber", source = "orderNumber", qualifiedByName = "getStringOrderNumber")
    OrderResponseDTO getOrderResponseDTOFromEntity(Order order);
    Order getOrderEntityFromDTO(OrderResponseDTO orderResponseDTO);
    List<OrderResponseDTO> getListOrderResponseDTOFromEntities(List<Order> orderList);
    List<Fleet> getListOrderEntityFromDTOs(List<OrderResponseDTO> orderResponseDTOS);
    @Named("getStringOrderNumber")
    default String getStringOrderNumber(UUID orderNumber) {
        return orderNumber.toString();
    }
}
