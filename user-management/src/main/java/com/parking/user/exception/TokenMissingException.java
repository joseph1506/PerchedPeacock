package com.parking.user.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenMissingException extends AuthenticationException {
    public TokenMissingException(String msg) {
        super(msg);
    }
}
