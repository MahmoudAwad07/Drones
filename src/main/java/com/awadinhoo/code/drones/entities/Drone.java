package com.awadinhoo.code.drones.entities;


import com.awadinhoo.code.drones.enums.DroneModel;
import com.awadinhoo.code.drones.enums.DroneState;
import com.awadinhoo.code.drones.services.DroneStateService;
import com.awadinhoo.code.drones.services.impl.states.IdleStateService;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "DRONES")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long droneId;

    @Column(name = "SERIAL_NUMBER", nullable = false, unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "MODEL", nullable = false)
    private DroneModel model;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false)
    private DroneState state;

    @Column(name = "WEIGHT_LIMIT_IN_GRAMS", nullable = false)
    private Long weightLimitInGrams;

    @Column(name = "BATTERY_CAPACITY", nullable = false)
    private Integer batteryCapacity;

    @JoinColumn(name = "FLEET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fleet fleet;

    @OneToMany(mappedBy = "drone")
    private List<Order> orders;

    @Column(name = "ACTIVE", nullable = false)
    private Integer active;

    @Column(name = "IS_DELETED")
    private Integer isDeleted = 0;

    @Transient
    private DroneStateService droneStateService = new IdleStateService();

    public void loadOrder(Order order) throws InterruptedException {
        droneStateService.loadOrder(this, order);
    }

    public void charge() throws InterruptedException {
        droneStateService.charge(this);
    }

    public void deliverOrder(Order order) throws InterruptedException{
        droneStateService.deliverOrder(this, order);
    }

}
