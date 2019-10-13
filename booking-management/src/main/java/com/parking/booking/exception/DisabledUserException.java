package com.parking.booking.exception;

public class DisabledUserException extends Exception {

    public DisabledUserException(String msg) {
        super(msg);
    }
}
