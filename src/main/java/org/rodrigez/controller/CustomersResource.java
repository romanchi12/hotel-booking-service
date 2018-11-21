package org.rodrigez.controller;

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
            CustomerDTO dto = convertToDTO(customer);
            return new ApiResponse(Status.OK, dto);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, e.getMessage());
        }
    }

    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDTO dto){
        try {
            Customer customer = convertToEntity(dto);
            customerService.add(customer);
            CustomerDTO newCustomerDTO = convertToDTO(customer);
            return new ApiResponse(Status.OK, newCustomerDTO);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, e.getMessage());
        }

    }

    @GetMapping(value = "/{customerId}/bookings")
    public ApiResponse getCustomerBookings(@PathVariable(value = "customerId") long customerId){
        try {
            List<Booking> bookingList = bookingService.getCustomerBookings(customerId);
            List<BookingDTO> dtoList = bookingList.stream().map(this::convertToDTO).collect(Collectors.toList());
            return new ApiResponse(Status.OK, dtoList);
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, e.getMessage());
        }
    }

    private BookingDTO convertToDTO(Booking booking){
        return new BookingDTO(booking);
    }

    private CustomerDTO convertToDTO(Customer customer){
        return new CustomerDTO(customer);
    }

    private Customer convertToEntity(CustomerDTO dto){
        return dto.toEntity();
    }
}