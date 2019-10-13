package com.parking.user.constants;

public enum AppConstants {
    UNAUTHORIZED("Unauthorized"),
    AUTHORIZATION("Authorization"),
    BEARER("Bearer"),
    ACTIVE("ACTIVE"),
    IN_ACTIVE("INACTIVE"),
    USER_CREATION_SUCCESS("User successfully registered"),
    USER_DELETION_SUCCESS("User successfully deleted"),
    USER_MODIFICATION_SUCCESS("User details modified"),
    USER_PASSWORD_CHANGED("User password modified"),
    VEHICLE_NOT_FOUND("Vehicle not present in database"),
    VEHICLE_EXISTS("Vehicle with the registration already exists"),
    VEHICLE_ADDED_SUCCESS("Vehicle Added successfully"),
    VEHICLE_MODIFIED_SUCCESS("Vehicle Modified successfully"),
    VEHICLE_DELETED_SUCCESS("Vehicle Deleted successfully"),
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
