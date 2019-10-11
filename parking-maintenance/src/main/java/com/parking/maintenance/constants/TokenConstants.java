package com.parking.maintenance.constants;

public enum TokenConstants {
    ROLES("roles"),
    INVALID_JWT_SIGNATURE("Invalid JWT signature"),
    INVALID_JWT_TOKEN("Invalid JWT token"),
    EXPIRED_JWT_TOKEN("Expired JWT token"),
    UNSUPPORTED_JWT_TOKEN("Unsupported JWT token"),
    JWT_TOKEN_MISSING("No JWT token found in the request headers"),
    JWT_STRING_EMPTY("JWT claims string is empty.");

    private String value;
    TokenConstants(String value) {
        this.value= value;
    }

    public String getValue() {
        return value;
    }
}
