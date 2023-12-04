package com.awadinhoo.code.drones.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "ZONES")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zoneId;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "AVG_TIME_MINUTES_FOR_LIGHT_WEIGHT_DRONE", nullable = false)
    private Integer maxTimeMinutesForLightWeightDrone;

    @Column(name = "MAX_TIME_MINUTES_FOR_MIDDLE_WEIGHT_DRONE", nullable = false)
    private Integer maxTimeMinutesForMiddleWeightDrone;

    @Column(name = "MAX_TIME_MINUTES_FOR_CRUISE_WEIGHT_DRONE", nullable = false)
    private Integer maxTimeMinutesForCruiseWeightDrone;

    @Column(name = "MAX_TIME_MINUTES_FOR_HEAVY_WEIGHT_DRONE", nullable = false)
    private Integer maxTimeMinutesForHeavyWeightDrone;

    @Column(name = "MAX_BATTERY_CAPACITY_CONSUMED_PER_TRIP", nullable = false)
    private Integer maxBatteryCapacityConsumedPerTrip;

    @JoinColumn(name = "FLEET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fleet fleet;

    @OneToMany(mappedBy = "zone")
    private List<Order> orders;

    @Column(name = "ACTIVE", nullable = false)
    private Integer active;

    @Column(name = "IS_DELETED", nullable = false)
    private Integer isDeleted = 0;
}
