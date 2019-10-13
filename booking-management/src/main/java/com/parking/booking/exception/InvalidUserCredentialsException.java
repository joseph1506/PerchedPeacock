package com.parking.booking.exception;

public class InvalidUserCredentialsException extends Exception {
    public InvalidUserCredentialsException(String msg) {
        super(msg);
    }
}
