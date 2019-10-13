package com.parking.user.exception;

public class VehicleAlreadyExistsException extends Exception {
    public VehicleAlreadyExistsException(String message) {
        super(message);
    }
}
