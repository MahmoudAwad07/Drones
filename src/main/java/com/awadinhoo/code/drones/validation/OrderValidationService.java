package com.awadinhoo.code.drones.validation;


import com.awadinhoo.code.drones.entities.Order;


public interface OrderValidationService {

    Order checkOrderExistsAndGetIfFound(Long orderId);
    void checkOrderStatusIsValidForCancel(Order order);
}
