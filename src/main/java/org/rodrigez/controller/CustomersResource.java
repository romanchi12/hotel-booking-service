package org.rodrigez.controller;

import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.model.dto.CustomerDTO;
import org.rodrigez.service.BookingService;
import org.rodrigez.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomersResource {
    @Autowired
    CustomerService customerService;
    @Autowired
    BookingService bookingService;

    @GetMapping(value = "/{customerId}")
    public ResponseEntity getCustomer(@PathVariable(value = "customerId") long customerId){
        Customer customer = customerService.getCustomer(customerId);
        CustomerDTO dto = new CustomerDTO(customer);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody CustomerDTO dto){
        Customer customer = dto.toEntity();
        customerService.add(customer);
        CustomerDTO newDTO = new CustomerDTO(customer);
        return new ResponseEntity<>(newDTO, HttpStatus.CREATED);

    }

    @GetMapping(value = "/{customerId}/bookings")
    public ResponseEntity getCustomerBookings(@PathVariable(value = "customerId") long customerId){
        List<Booking> bookingList = bookingService.getCustomerBookings(customerId);
        List<BookingDTO> dtoList = bookingList.stream().map(BookingDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}