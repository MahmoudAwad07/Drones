package com.awadinhoo.code.drones.enums;


import lombok.Getter;

@Getter
public enum DeleteStatus {

    NOT_DELETED ("Not Deleted", 0),

    DELETED ("Deleted", 1);

    private final String statusName;
    private final Integer statusId;


    DeleteStatus(String statusName, Integer statusId) {
        this.statusName = statusName;
        this.statusId = statusId;
    }
}
