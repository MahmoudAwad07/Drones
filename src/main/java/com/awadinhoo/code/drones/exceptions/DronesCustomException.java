package com.awadinhoo.code.drones.exceptions;



public class DronesCustomException extends RuntimeException {

    public DronesCustomException(String message) {
        super(message);
    }
    public DronesCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
