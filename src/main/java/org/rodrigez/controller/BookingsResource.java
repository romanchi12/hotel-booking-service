package org.rodrigez.controller;

import org.modelmapper.ModelMapper;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bookings")
public class BookingsResource {
    @Autowired
    BookingService bookingService;
    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/{bookingId}",method = RequestMethod.GET)
    public BookingDTO getBooking(@PathVariable("bookingId") long bookingId){
        Booking booking = bookingService.getBooking(bookingId);
        return convertToDTO(booking);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookingDTO> getBookings(){
        List<Booking> entityList = bookingService.getBookings();
        return entityList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public BookingDTO addBooking(@RequestBody BookingDTO bookingDTO) throws ParseException {
        Booking booking = convertToEntity(bookingDTO);
        bookingService.add(booking);
        return convertToDTO(booking);
    }

    private BookingDTO convertToDTO(Booking booking){
        BookingDTO dto = modelMapper.map(booking, BookingDTO.class);
        dto.setFromDate(booking.getFrom());
        dto.setUntilDate(booking.getUntil());
        return dto;
    }

    private Booking convertToEntity(BookingDTO dto) throws ParseException {
        Booking booking = modelMapper.map(dto, Booking.class);
        booking.setFrom(dto.getFromDate());
        booking.setUntil(dto.getUntilDate());
        return booking;
    }
}