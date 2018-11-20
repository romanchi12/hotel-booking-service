package org.rodrigez.service.impl;

import org.rodrigez.model.domain.*;
import org.rodrigez.repository.BookingOptionRepository;
import org.rodrigez.repository.BookingRepository;
import org.rodrigez.service.*;
import org.rodrigez.util.DateRange;
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
    public Booking add(Booking booking){

        Customer customer = customerService.getCustomer(booking.getCustomer().getId());
        booking.setCustomer(customer);
        customer.addBooking(booking);

        long roomId = booking.getRoom().getId();
        Room room = inventoryService.getRoom(roomId);
        booking.setRoom(room);



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
        int days = (int) DateRange.daysInRange(booking.getFrom(),booking.getUntil());
        int summaryPrice = days * oneDayPrice;
        booking.setRoomPrice(roomPrice);
        booking.setSummaryPrice(summaryPrice);

        bookingRepository.save(booking);

        return booking;
    }
}