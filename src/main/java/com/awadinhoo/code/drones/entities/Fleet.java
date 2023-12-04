package com.awadinhoo.code.drones.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "FLEETS")
public class Fleet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fleetId;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "fleet", cascade = CascadeType.MERGE)
    private List<Zone> supportedZones = new ArrayList<>();

    @Column(name = "MAX_NUMBER_OF_DRONES", nullable = false)
    private Integer maxNumberOfDrones;

    @OneToMany(mappedBy = "fleet")
    private List<Drone> drones = new ArrayList<>();

    @Column(name = "ACTIVE", nullable = false)
    private Integer active;

    @Column(name = "IS_DELETED")
    private Integer isDeleted = 0;

}
