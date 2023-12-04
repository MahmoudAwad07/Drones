package com.awadinhoo.code.drones.validation.impl;

import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.dtos.OrderRequestDTO;
import com.awadinhoo.code.drones.entities.Drone;
import com.awadinhoo.code.drones.entities.Order;
import com.awadinhoo.code.drones.entities.Zone;
import com.awadinhoo.code.drones.enums.OrderStatus;
import com.awadinhoo.code.drones.exceptions.DronesCustomException;
import com.awadinhoo.code.drones.exceptions.ResourceNotFoundException;
import com.awadinhoo.code.drones.repositories.OrderRepository;
import com.awadinhoo.code.drones.validation.OrderValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceValidationImpl implements OrderValidationService {


    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceValidationImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order checkOrderExistsAndGetIfFound(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if(optionalOrder.isEmpty()){
            throw new ResourceNotFoundException(Constants.StatusMessages.ORDER_NOT_FOUND_MESSAGE + orderId);
        }

        return optionalOrder.get();
    }

    @Override
    public void checkOrderStatusIsValidForCancel(Order order) {

        if(order.getOrderStatus().equals(OrderStatus.CANCELLED)){
            throw new DronesCustomException(Constants.StatusMessages.ORDER_IS_ALREADY_CANCELLED_MESSAGE);
        }else if(!order.getOrderStatus().equals(OrderStatus.PLACED)){
            throw new DronesCustomException(Constants.StatusMessages.ORDER_CAN_NOT_CANCELLED_MESSAGE);
        }

    }
}
