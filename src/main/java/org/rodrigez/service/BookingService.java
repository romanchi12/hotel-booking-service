package org.rodrigez.service;

import org.rodrigez.model.Booking;
import org.rodrigez.model.Room;
import org.rodrigez.validation.BookingRequest;
import org.rodrigez.validation.DateRange;

import java.util.List;

public interface BookingService {
    Booking getBooking(long bookingId);
    boolean isAvailableRoom(Room room, DateRange dateRange);
    List<Booking> getBookings();
    Booking add(BookingRequest request);
}
