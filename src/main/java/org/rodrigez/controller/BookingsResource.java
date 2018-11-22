package org.rodrigez.controller;

import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.model.dto.BookingPriceDTO;
import org.rodrigez.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bookings")
public class BookingsResource {
    @Autowired
    BookingService bookingService;

    @GetMapping(value = "/{bookingId}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable("bookingId") long bookingId){
        Booking booking = bookingService.getBooking(bookingId);
        BookingDTO bookingDTO = new BookingDTO(booking);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping(value = "/{bookingId}/price")
    public ResponseEntity getBookingPrice(@PathVariable("bookingId") long bookingId){
        Booking booking = bookingService.getBooking(bookingId);
        BookingPriceDTO priceDTO = new BookingPriceDTO(booking);
        return ResponseEntity.ok(priceDTO);
    }

    @GetMapping
    public ResponseEntity getBookings(){
        List<Booking> entityList = bookingService.getBookings();
        List<BookingDTO> dtoList = entityList.stream().map(BookingDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity addBooking(@RequestBody BookingDTO bookingDTO) throws Exception {
        Booking booking = bookingDTO.toEntity();
        booking = bookingService.add(booking);
        BookingDTO newBookingDTO = new BookingDTO(booking);
        return new ResponseEntity<>(newBookingDTO, HttpStatus.CREATED);
    }
}