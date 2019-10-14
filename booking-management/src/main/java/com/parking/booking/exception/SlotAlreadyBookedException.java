package com.parking.booking.exception;

public class SlotAlreadyBookedException extends Exception {
    public SlotAlreadyBookedException(String message) {
        super(message);
    }
}
