package com.parking.maintenance.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenMissingException extends AuthenticationException {
    public TokenMissingException(String msg) {
        super(msg);
    }
}
