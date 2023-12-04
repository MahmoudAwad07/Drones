package com.awadinhoo.code.drones.controllers;


import com.awadinhoo.code.drones.constants.Constants;
import com.awadinhoo.code.drones.dtos.DroneDTO;
import com.awadinhoo.code.drones.dtos.MedicationDTO;
import com.awadinhoo.code.drones.dtos.OrderRequestDTO;
import com.awadinhoo.code.drones.dtos.OrderResponseDTO;
import com.awadinhoo.code.drones.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {

        OrderResponseDTO orderResponseDTO = orderService.placeOrder(orderRequestDTO);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);

    }

    @PutMapping("cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") Long orderId){

        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(Constants.StatusMessages.ORDER_SUCCESSFULLY_CANCELLED_MESSAGE, HttpStatus.OK);
    }



}
