package com.parking.booking.controller;

import com.parking.booking.entity.Booking;
import com.parking.booking.exception.BookingNotFoundException;
import com.parking.booking.model.SlotAvailablityReq;
import com.parking.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingResource {

    @Autowired
    private BookingService bookingService;

    // active Bookings
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/activeBookings/{userId}")
    public ResponseEntity<List<Booking>> getActiveBookings(@PathVariable String userId){
        return bookingService.getActiveBookings(userId);
    }

    // Expired and cancelled bookings

    // All Bookings
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/allBookings/{userId}")
    public ResponseEntity<List<Booking>> getAllBookings(@PathVariable String userId){
        return bookingService.getAllBookings(userId);
    }

    // Booking Details -- to include country, center, slot, and vehicle and time details
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/bookingDetails/{bookingId}")
    public ResponseEntity<Booking> getBookingDetails(@PathVariable String bookingId) throws Exception {
        return bookingService.getBookingDetails(bookingId);
    }

    // Cancel a Booking
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/cancelBooking")
    public ResponseEntity<String> cancelBooking(@RequestBody String bookingId)  throws Exception{
        return bookingService.cancelBooking(bookingId);
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/bookingAvailableSlots")
    public ResponseEntity<List<String>> getBookingAvailableSlots(@RequestBody SlotAvailablityReq request){
        return bookingService.getBookingAvailableSlots(request);
    }

    // Book a slot for Vehicle and a time period
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/bookSlot")
    public ResponseEntity<String> bookSlot(@RequestBody Booking booking){
        return bookingService.bookSlot(booking);
    }
}
