package org.rodrigez.controller;

import org.rodrigez.model.Booking;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.service.BookingService;
import org.rodrigez.validation.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bookings")
public class BookingsResource {
    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/{bookingId}",method = RequestMethod.GET)
    public BookingDTO getBooking(@PathVariable("bookingId") long bookingId){
        Booking booking = bookingService.getBooking(bookingId);
        return new BookingDTO(booking);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookingDTO> getBookings(){
        List<Booking> entityList = bookingService.getBookings();
        return entityList.stream().map(BookingDTO::new).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public BookingDTO addBooking(@RequestBody BookingRequest request) {
        Booking booking = bookingService.add(request);
        return new BookingDTO(booking);
    }

}