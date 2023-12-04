package com.awadinhoo.code.drones.services;


import com.awadinhoo.code.drones.dtos.OrderRequestDTO;
import com.awadinhoo.code.drones.dtos.OrderResponseDTO;
import com.awadinhoo.code.drones.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface OrderService {

    OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO);
    void cancelOrder(Long orderId);
    Optional<List<Order>> getNewOrders();
}
