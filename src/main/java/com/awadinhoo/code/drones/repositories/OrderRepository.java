package com.awadinhoo.code.drones.repositories;

import com.awadinhoo.code.drones.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findAllByOrderStatus(String orderStatus);

}
