package com.awadinhoo.code.drones.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "DRONE_AUDIT")
public class DroneAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @ManyToOne
    @JoinColumn(name = "DRONE_ID")
    private Drone drone;

    @Column(name = "BATTERY_LEVEL")
    private Integer batteryLevel;

    @Column(name = "AUDIT_TIME")
    private LocalDateTime auditTime;
}
