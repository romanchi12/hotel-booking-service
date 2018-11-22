package org.rodrigez.controller;

import org.rodrigez.controller.response.ApiError;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.model.dto.CustomerDTO;
import org.rodrigez.service.BookingService;
import org.rodrigez.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ApiResponse getCustomer(@PathVariable(value = "customerId") long customerId){
        try {
            Customer customer = customerService.getCustomer(customerId);
            CustomerDTO dto = new CustomerDTO(customer);
            return new ApiResponse(Status.OK, dto);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }

    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDTO dto){
        try {
            Customer customer = dto.toEntity();
            customerService.add(customer);
            CustomerDTO newDTO = new CustomerDTO(customer);
            return new ApiResponse(Status.OK, newDTO);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }

    }

    @GetMapping(value = "/{customerId}/bookings")
    public ApiResponse getCustomerBookings(@PathVariable(value = "customerId") long customerId){
        try {
            List<Booking> bookingList = bookingService.getCustomerBookings(customerId);
            List<BookingDTO> dtoList = bookingList.stream().map(BookingDTO::new).collect(Collectors.toList());
            return new ApiResponse(Status.OK, dtoList);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }
}