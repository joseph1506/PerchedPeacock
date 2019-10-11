package com.parking.user.constants;

public enum AppConstants {
    UNAUTHORIZED("Unauthorized"),
    AUTHORIZATION("Authorization"),
    BEARER("Bearer"),
    USER_CREATION_SUCCESS("User successfully registered"),
    USER_DELETION_SUCCESS("User successfully deleted"),
    USER_MODIFICATION_SUCCESS("User details modified"),
    USER_PASSWORD_CHANGED("User password modified"),
    MASK("********"),
    LOGOUT_SUCCESS("Logged out successfully"),
    COMMA_DELIMITER(",");

    private String value;

    AppConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
