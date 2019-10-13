package com.parking.booking.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenMalformedException extends AuthenticationException {
    public TokenMalformedException(String msg) {
        super(msg);
    }
}
