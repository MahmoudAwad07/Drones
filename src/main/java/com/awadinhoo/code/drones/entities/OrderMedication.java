package com.awadinhoo.code.drones.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ORDERS_MEDICATIONS")
public class OrderMedication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderMedicationId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "MEDICATION_ID")
    private Medication medication;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;
}
