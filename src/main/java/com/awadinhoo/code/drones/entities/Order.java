package com.awadinhoo.code.drones.entities;


import com.awadinhoo.code.drones.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "ORDER_NUMBER", nullable = false)
    private UUID orderNumber = UUID.randomUUID();

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @JoinColumn(name = "ZONE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;

    @JoinColumn(name = "DRONE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Drone drone;

    @OneToMany(mappedBy = "order")
    private List<OrderMedication> orderMedications = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "ORDER_TIME", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "ESTIMATED_DELIVERY_TIME_IN_MINUTES", nullable = false)
    private Integer estimatedDeliveryTimeInMinutes;

    @Column(name = "ORDER_DELIVERED_TIME")
    private LocalDateTime orderDeliveredTime;

}
