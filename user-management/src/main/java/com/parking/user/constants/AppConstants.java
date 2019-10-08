package com.parking.user.constants;

public enum AppConstants {
    UNAUTHORIZED("Unauthorized"),
    AUTHORIZATION("Authorization"),
    BEARER("Bearer"),
    USER("USER"),
    ADMIN("ADMIN"),
    COMMA_DELIMITER(",");

    private String value;

    AppConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
