package org.rodrigez.controller;

import org.rodrigez.controller.response.ApiError;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.model.dto.BookingPriceDTO;
import org.rodrigez.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bookings")
public class BookingsResource {
    @Autowired
    BookingService bookingService;

    @GetMapping(value = "/{bookingId}")
    public ApiResponse getBooking(@PathVariable("bookingId") long bookingId){
        try {
            Booking booking = bookingService.getBooking(bookingId);
            BookingDTO bookingDTO = new BookingDTO(booking);
            return new ApiResponse(Status.OK, bookingDTO);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }

    @GetMapping(value = "/{bookingId}/price")
    public ApiResponse getBookingPrice(@PathVariable("bookingId") long bookingId){
        try {
            Booking booking = bookingService.getBooking(bookingId);
            BookingPriceDTO priceDTO = new BookingPriceDTO(booking);
            return new ApiResponse(Status.OK, priceDTO);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }

    @GetMapping
    public ApiResponse getBookings(){
        try {
            List<Booking> entityList = bookingService.getBookings();
            List<BookingDTO> dtoList = entityList.stream().map(BookingDTO::new).collect(Collectors.toList());
            return new ApiResponse(Status.OK, dtoList);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }

    @PostMapping
    public ApiResponse addBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            Booking booking = bookingDTO.toEntity();
            booking = bookingService.add(booking);
            BookingDTO newBookingDTO = new BookingDTO(booking);
            return new ApiResponse(Status.OK, newBookingDTO);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }
}