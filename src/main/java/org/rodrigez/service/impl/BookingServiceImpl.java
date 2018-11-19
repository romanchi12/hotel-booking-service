package org.rodrigez.service.impl;

import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.domain.RoomOption;
import org.rodrigez.repository.BookingOptionRepository;
import org.rodrigez.repository.BookingRepository;
import org.rodrigez.service.*;
import org.rodrigez.validation.BookingRequest;
import org.rodrigez.validation.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BookingOptionRepository bookingOptionRepository;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    CustomerService customerService;

    @Override
    public Booking getBooking(long bookingId) {
        return bookingRepository.getOne(bookingId);
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking add(BookingRequest bookingRequest) {
        Booking booking = create(bookingRequest);
        bookingRepository.save(booking);
        //bookingOptionRepository.saveAll(booking.getSelectedOptionList());
        return booking;
    }

    private Booking create(BookingRequest request){

        Booking booking = new Booking();

        Customer customer = customerService.getCustomer(request.getCustomerId());
        customer.addBooking(booking);
        booking.setCustomer(customer);

        Room room = inventoryService.getRoom(request.getRoomId());
        booking.setRoom(room);

        DateRange dateRange = new DateRange(request.getFrom(), request.getUntil());
        booking.setFrom(dateRange.getFrom());
        booking.setUntil(dateRange.getUntil());

        booking.setRoomPrice(room.getCurrentPrice());

        int optionsPrice = 0;
        for(RoomOption roomOption : inventoryService.getSuggestedOptions(request.getRoomId(),request.getOptionList())){
            booking.addBookingOption(roomOption.getOptionType(), roomOption.getPrice());
            optionsPrice =+ roomOption.getPrice();
        }

        int roomPrice = room.getCurrentPrice();
        booking.setRoomPrice(roomPrice);
        booking.setSummaryPrice(optionsPrice + roomPrice);

        return booking;
    }
}