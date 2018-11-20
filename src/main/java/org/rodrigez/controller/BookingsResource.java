package org.rodrigez.controller;

import org.modelmapper.ModelMapper;
import org.rodrigez.controller.response.ApiError;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
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

    @GetMapping(value = "/{bookingId}")
    public ApiResponse getBooking(@PathVariable("bookingId") long bookingId){
        try {
            Booking booking = bookingService.getBooking(bookingId);
            return new ApiResponse(Status.OK, convertToDTO(booking));
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }

    @GetMapping
    public ApiResponse getBookings(){
        try {
            List<Booking> entityList = bookingService.getBookings();
            List<BookingDTO> dtoList = entityList.stream().map(this::convertToDTO).collect(Collectors.toList());
            return new ApiResponse(Status.OK, dtoList);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }

    @PostMapping
    public ApiResponse addBooking(@RequestBody BookingDTO bookingDTO) throws ParseException {
        try {
            Booking booking = convertToEntity(bookingDTO);
            bookingService.add(booking);
            return new ApiResponse(Status.OK, convertToDTO(booking));
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
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