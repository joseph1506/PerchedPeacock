package com.parking.booking.constants;

public enum AppConstants {
    UNAUTHORIZED("Unauthorized"),
    AUTHORIZATION("Authorization"),
    BEARER("Bearer"),
    //MASK("********"),
    //ACTIVE("Y"),
    //INACTIVE("N"),
    //CENTER_CREATE_SUCCESSFULL("Center created successfully"),
    //CENTER_MODIFY_SUCCESSFULL("Center modified successfully"),
    //CENTER_DELETE_SUCCESSFULL("Center deleted successfully"),
    //SLOT_DELETE_SUCCESSFULL("Slot deleted successfully"),
    //SLOT_ADDITION_SUCCESSFULL("Slot added successfully"),
    //AVAILABLE("AVAILABLE"),
    //BOOKED("BOOKED"),
    //DELETED("DELETED"),
    COMMA_DELIMITER(",");

    private String value;

    AppConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
