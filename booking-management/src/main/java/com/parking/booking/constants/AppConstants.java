package com.parking.booking.constants;

public enum AppConstants {
    UNAUTHORIZED("Unauthorized"),
    AUTHORIZATION("Authorization"),
    BEARER("Bearer"),
    EXPIRED("EXPIRED"),
    ACTIVE("ACTIVE"),
    CANCELLED("CANCELLED"),
    BOOKING_NOT_FOUND("Booking not found in database"),
    BOOKING_CANCEL_SUCCESS("Booking cancelled successfully"),
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
