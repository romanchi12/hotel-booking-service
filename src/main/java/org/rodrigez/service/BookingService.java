package org.rodrigez.service;

import org.rodrigez.model.domain.Booking;

import java.util.List;

public interface BookingService {
    Booking getBooking(long bookingId);
    List<Booking> getBookings();
    Booking add(Booking booking);
}
