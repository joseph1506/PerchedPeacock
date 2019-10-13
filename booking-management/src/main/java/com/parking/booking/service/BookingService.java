package com.parking.booking.service;

import com.parking.booking.constants.AppConstants;
import com.parking.booking.entity.Booking;
import com.parking.booking.exception.BookingNotFoundException;
import com.parking.booking.model.SlotAvailablityReq;
import com.parking.booking.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public ResponseEntity<List<Booking>> getActiveBookings(String userId) {
        List<Booking> activeBookings = bookingRepository.findBookingsByUserIdAndStatus(userId, AppConstants.ACTIVE.getValue());
        return new ResponseEntity<>(activeBookings, HttpStatus.OK);
    }

    public ResponseEntity<List<Booking>> getAllBookings(String userId) {
        return new ResponseEntity<>(bookingRepository.findBookingsByUserId(userId), HttpStatus.OK);
    }

    public ResponseEntity<Booking> getBookingDetails(String bookingId) throws BookingNotFoundException {
        Booking booking = getBookingEntity(bookingId);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }

    private Booking getBookingEntity(String bookingId) throws BookingNotFoundException {
        Optional<Booking> bookingOptional= bookingRepository.findById(bookingId);
        if(bookingOptional.isPresent()){
            return bookingOptional.get();
        } else {
            throw new BookingNotFoundException(AppConstants.BOOKING_NOT_FOUND.getValue());
        }
    }

    public ResponseEntity<String> cancelBooking(String bookingId) throws BookingNotFoundException {
        Booking booking = getBookingEntity(bookingId);
        booking.setStatus(AppConstants.CANCELLED.getValue());
        bookingRepository.save(booking);
        return getMessageResponseEntity(AppConstants.BOOKING_CANCEL_SUCCESS.getValue(),HttpStatus.OK);
    }

    private ResponseEntity<String> getMessageResponseEntity(String message,HttpStatus status){
        return new ResponseEntity<>(message,status);
    }

    public ResponseEntity<String> bookSlot(Booking booking) {
        // last check if slot is available or not
        // if available book
        return null;
    }

    public ResponseEntity<List<String>> getBookingAvailableSlots(SlotAvailablityReq request) {
        /*request.getSlotIds().stream()
                .forEach();*/
        return null;
    }
}
