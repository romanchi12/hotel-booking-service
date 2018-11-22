package org.rodrigez.service;

import org.rodrigez.model.domain.Booking;
import org.rodrigez.service.exceptions.DateIntervalException;
import org.rodrigez.service.exceptions.NotAvailableRoomException;
import org.rodrigez.service.exceptions.NotFoundException;

import java.util.List;

public interface BookingService {
    /**
     * Returns booking with Id {@param bookingId}
     * @param bookingId given Id
     * @return booking
     * @throws NotFoundException if booking with {@param bookingId} is missing
     */
    Booking getBooking (long bookingId);

    /**
     * @return all available bookings from repository
     */
    List<Booking> getBookings();

    /**
     * @param customerId Id of checked customer
     * @return list of bookings from customer
     */
    List<Booking> getCustomerBookings(long customerId);

    /**
     * @param booking Request mapped to Booking
     * @return new Booking object
     * @throws NotFoundException if customerId, roomId, optionTypeId or roomOption is noyt-available
     * @throws NotAvailableRoomException if room from request is not available for date interval
     * @throws DateIntervalException if data interval is invalid
     */
    Booking add(Booking booking) throws Exception;
}
