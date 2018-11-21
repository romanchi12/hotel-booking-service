package org.rodrigez.service;

import org.rodrigez.model.domain.Booking;
import org.rodrigez.service.exceptions.NotAvailableRoomException;

import java.util.List;

public interface BookingService {
    Booking getBooking(long bookingId);
    List<Booking> getBookings();
    List<Booking> getCustomerBookings(long customerId);
    Booking add(Booking booking) throws Exception;
}
