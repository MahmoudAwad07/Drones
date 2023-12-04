package com.awadinhoo.code.drones.services.impl;

import com.awadinhoo.code.drones.dtos.OrderMedicationDTO;
import com.awadinhoo.code.drones.dtos.OrderRequestDTO;
import com.awadinhoo.code.drones.dtos.OrderResponseDTO;
import com.awadinhoo.code.drones.entities.*;
import com.awadinhoo.code.drones.enums.OrderStatus;
import com.awadinhoo.code.drones.mappers.OrderMapper;
import com.awadinhoo.code.drones.repositories.OrderRepository;
import com.awadinhoo.code.drones.services.OrderService;
import com.awadinhoo.code.drones.validation.DroneValidationService;
import com.awadinhoo.code.drones.validation.MedicationValidationService;
import com.awadinhoo.code.drones.validation.OrderValidationService;
import com.awadinhoo.code.drones.validation.ZoneValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderValidationService orderValidationService;

    private final OrderMapper orderMapper;

    private final ZoneValidationService zoneValidationService;

    private final MedicationValidationService medicationValidationService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderValidationService orderValidationService,
                            OrderMapper orderMapper,
                            DroneValidationService droneValidationService,
                            ZoneValidationService zoneValidationService,
                            MedicationValidationService medicationValidationService) {
        this.orderRepository = orderRepository;
        this.orderValidationService = orderValidationService;
        this.orderMapper = orderMapper;
        this.zoneValidationService = zoneValidationService;
        this.medicationValidationService = medicationValidationService;
    }

    @Override
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {

        List<OrderMedicationDTO> orderMedications = orderRequestDTO.getOrderMedications();

        Zone zone = zoneValidationService.checkZoneAndGetIfFound(orderRequestDTO.getZoneId());
        zoneValidationService.checkZoneIsActive(zone);

        List<Long> medicationsIds = orderMedications.stream().map(OrderMedicationDTO::getMedicationId).toList();
        Map<Long, Medication> medications = medicationValidationService.checkMedicationsAndGet(medicationsIds);

        medicationValidationService.checkMedicationQuantity(medications, orderMedications);

        Order order = orderMapper.getOrderFromOrderRequestDTO(orderRequestDTO);

        updateOrderEntity(order, zone, medications, getOrderMedications(orderMedications, medications, order));
        return orderMapper.getOrderResponseDTOFromEntity(orderRepository.save(order));

    }

    private void updateOrderEntity(Order order, Zone zone, Map<Long, Medication> medications,
                                   List<OrderMedication> orderMedications ) {

        order.setOrderStatus(OrderStatus.PLACED);
        order.setOrderTime(LocalDateTime.now());
        order.setEstimatedDeliveryTimeInMinutes(zone.getMaxTimeMinutesForCruiseWeightDrone());
        order.setOrderMedications(orderMedications);
        zone.getOrders().add(order);
        order.setZone(zone);
    }

    private List<OrderMedication> getOrderMedications(List<OrderMedicationDTO> orderMedicationsDTOs,
                                                      Map<Long, Medication> medications, Order order) {

        return orderMedicationsDTOs.stream().map(orderMedicationDTO -> {
            OrderMedication orderMedication = new OrderMedication();
            orderMedication.setMedication(medications.get(orderMedicationDTO.getMedicationId()));
            orderMedication.setQuantity(orderMedicationDTO.getQuantity());
            orderMedication.setOrder(order);
            return orderMedication;
        }).toList();
    }


    @Override
    public void cancelOrder(Long orderId) {

        Order order = orderValidationService.checkOrderExistsAndGetIfFound(orderId);
        orderValidationService.checkOrderStatusIsValidForCancel(order);
        order.setOrderStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);
    }

    @Override
    public Optional<List<Order>> getNewOrders() {
        return orderRepository.findAllByOrderStatus(OrderStatus.PLACED.toString());
    }
}
