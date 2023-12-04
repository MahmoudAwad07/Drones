package com.awadinhoo.code.drones.enums;

import lombok.Getter;

@Getter
public enum DroneModel {

    // Lightweight, MiddleWeight, CruiserWeight, HeavyWeight
    LIGHTWEIGHT ("LightWeight", 1),
    MIDDLEWEIGHT ("MiddleWeight", 2),
    CRUISEWEIGHT ("CruiserWeight", 3),
    HEAVYWEIGHT ("HeavyWeight", 4);

    private final String modelName;
    private final Integer modelId;

    DroneModel(String modelName , int modelId) {
        this.modelName = modelName;
        this.modelId = modelId;
    }

}
