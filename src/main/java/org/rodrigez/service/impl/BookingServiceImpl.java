package org.rodrigez.service.impl;

import org.rodrigez.model.Booking;
import org.rodrigez.model.Option;
import org.rodrigez.model.Room;
import org.rodrigez.model.User;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.repository.BookingRepository;
import org.rodrigez.service.BookingService;
import org.rodrigez.service.OptionService;
import org.rodrigez.service.RoomService;
import org.rodrigez.service.UserService;
import org.rodrigez.validation.BookingRequest;
import org.rodrigez.validation.DateRange;
import org.rodrigez.validation.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;
    @Autowired
    OptionService optionService;

    @Override
    public Booking getBooking(long bookingId) {
        return bookingRepository.getOne(bookingId);
    }

    @Override
    public boolean isAvailableRoom(Room room, DateRange dateRange) {
        List<Booking> bookingList = room.getBookingList();
        Date from, until;
        for(Booking booking : bookingList){
            from = booking.getFrom();
            until = booking.getUntil();
            if(dateRange.isInRange(from)||dateRange.isInRange(until)){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking add(BookingRequest bookingRequest) {
        Booking booking = create(bookingRequest);
        return bookingRepository.save(booking);
    }

    private Booking create(BookingRequest bookingRequest){
        BookingDTO dto = new BookingDTO(bookingRequest);

        UserRequest userRequest = new UserRequest(dto.getUserName());
        User user = userService.add(userRequest);
        Room room = roomService.getRoom(dto.getRoomId());
        List<Option> optionList = new LinkedList<>();
        for(long optionId: dto.getOptionIdList()){
            optionList.add(optionService.getOption(optionId));
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setFrom(dto.getFromDate());
        booking.setUntil(dto.getUntilDate());
        booking.setOptionList(optionList);
        booking.setPrice(dto.getPrice());

        return booking;
    }
}