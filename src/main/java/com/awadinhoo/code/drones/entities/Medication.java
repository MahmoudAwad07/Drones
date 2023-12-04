package com.awadinhoo.code.drones.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "MEDICATIONS")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicationId;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @Column(name = "WEIGHT_IN_GRAMS", nullable = false)
    private Integer weightInGrams;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    @Column(name = "IMAGE_FILE_NAME", unique = true)
    private String imageFileName;
    @Column(name = "ACTIVE", nullable = false)
    private Integer active;

    @Column(name = "IS_DELETED")
    private Integer isDeleted = 0;
}
