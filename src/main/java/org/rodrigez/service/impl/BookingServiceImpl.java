package org.rodrigez.service.impl;

import org.rodrigez.model.domain.*;
import org.rodrigez.repository.BookingOptionRepository;
import org.rodrigez.repository.BookingRepository;
import org.rodrigez.service.*;
import org.rodrigez.service.exceptions.NotAvailableRoomException;
import org.rodrigez.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    CustomerService customerService;
    @Autowired
    AvailabilityService availabilityService;

    @Override
    public Booking getBooking(long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(
                () -> new NotFoundException("Invalid bookingId " + bookingId));
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getCustomerBookings(long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return bookingRepository.findAllByCustomer(customer);
    }

    @Override
    public Booking add(Booking booking) throws Exception {

        long customerId = booking.getCustomer().getId();
        Customer customer = customerService.getCustomer(customerId);
        booking.setCustomer(customer);
        customer.addBooking(booking);

        long roomId = booking.getRoom().getId();
        Room room = inventoryService.getRoom(roomId);
        booking.setRoom(room);

        DateInterval interval = new DateInterval(booking.getFrom(), booking.getUntil());
        if(!availabilityService.isAvailableRoom(room, interval)){
            throw new NotAvailableRoomException("Not available room for date interval " + interval);
        }

        int optionsPrice = 0;
        for(BookingOption bookingOption: booking.getOptionList()){
            bookingOption.setBooking(booking);

            long optionTypeId = bookingOption.getOptionType().getId();
            OptionType optionType = inventoryService.getOptionType(optionTypeId);
            bookingOption.setOptionType(optionType);

            RoomOption roomOption = inventoryService.getRoomOption(roomId, optionTypeId);
            int price = roomOption.getPrice();
            bookingOption.setPrice(price);

            optionsPrice =+ price;
        }

        int roomPrice = room.getCurrentPrice();
        int oneDayPrice = roomPrice + optionsPrice;
        int days = daysBetween(booking.getFrom(),booking.getUntil());
        int summaryPrice = days * oneDayPrice;
        booking.setRoomPrice(roomPrice);
        booking.setSummaryPrice(summaryPrice);

        bookingRepository.save(booking);

        return booking;
    }

    private int daysBetween(Date from, Date until){
        long diff = until.getTime() - from.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}