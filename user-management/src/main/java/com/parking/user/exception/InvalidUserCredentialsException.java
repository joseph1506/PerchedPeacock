package com.parking.user.exception;

public class InvalidUserCredentialsException extends Exception {
    public InvalidUserCredentialsException(String msg) {
        super(msg);
    }
}
