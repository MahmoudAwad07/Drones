package com.awadinhoo.code.drones.schedulers;


import com.awadinhoo.code.drones.entities.*;
import com.awadinhoo.code.drones.enums.ActiveStatus;
import com.awadinhoo.code.drones.enums.DroneState;
import com.awadinhoo.code.drones.services.DroneService;
import com.awadinhoo.code.drones.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersScheduler {

    private final OrderService orderService;

    private final DroneService droneService;

    @Autowired
    public OrdersScheduler(OrderService orderService,
                           DroneService droneService) {
        this.orderService = orderService;
        this.droneService = droneService;
    }

    @Scheduled(fixedRate = 30000) // Execute every 30 seconds
    public void serveOrders() {
        Optional<List<Order>> optionalOrders = orderService.getNewOrders();
        optionalOrders.ifPresent(orderList -> orderList.forEach(this::handleNewOrder));
    }

    private void handleNewOrder(Order order) {

        Zone zone = order.getZone();
        Fleet fleet = zone.getFleet();

        List<Drone> availableDrones = fleet.getDrones().stream().filter(drone ->
                drone.getActive().equals(ActiveStatus.ACTIVE.getStatusId())
                        && drone.getState().equals(DroneState.IDLE)
                        && drone.getBatteryCapacity() > 25 ).toList();

        Integer orderSize = calculateOrderSize(order);

        Optional<Drone> optionalDrone = availableDrones.stream().filter(drone -> drone.getWeightLimitInGrams() > orderSize &&
                drone.getBatteryCapacity() > zone.getMaxBatteryCapacityConsumedPerTrip()).findFirst();

        if(optionalDrone.isPresent()){
            Drone drone = optionalDrone.get();
            droneService.loadDrone(drone, order);
        }
    }

    private Integer calculateOrderSize(Order order) {

        return order.getOrderMedications().stream()
                .mapToInt(orderMedication -> orderMedication.getQuantity() * orderMedication.getMedication().getWeightInGrams())
                .sum();
    }

}
