package com.parking.user.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenMalformedException extends AuthenticationException {
    public TokenMalformedException(String msg) {
        super(msg);
    }
}
