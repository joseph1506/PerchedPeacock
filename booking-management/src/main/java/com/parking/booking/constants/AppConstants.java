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
    SLOT_BOOKED("Slot not available for booking"),
    BOOKING_SUCCESS("Booking successfull. Your Booking id is::"),
    COMMA_DELIMITER(",");

    private String value;

    AppConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
