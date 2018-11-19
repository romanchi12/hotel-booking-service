package org.rodrigez.service;

import org.rodrigez.model.domain.Booking;
import org.rodrigez.validation.BookingRequest;

import java.util.List;

public interface BookingService {
    Booking getBooking(long bookingId);
    List<Booking> getBookings();
    Booking add(BookingRequest request);
}
