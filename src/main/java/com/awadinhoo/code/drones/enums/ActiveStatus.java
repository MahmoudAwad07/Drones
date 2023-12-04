package com.awadinhoo.code.drones.enums;


import lombok.Getter;

@Getter
public enum ActiveStatus {

    NOT_ACTIVE ("Not Active", 0),

    ACTIVE ("Active", 1);

    private final String statusName;
    private final Integer statusId;


    ActiveStatus(String statusName, Integer statusId) {
        this.statusName = statusName;
        this.statusId = statusId;
    }
}
