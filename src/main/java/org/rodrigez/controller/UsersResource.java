package org.rodrigez.controller;

import org.modelmapper.ModelMapper;
import org.rodrigez.configuration.ObjectMapperUtils;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.model.dto.CustomerDTO;
import org.rodrigez.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersResource {
    @Autowired
    CustomerService customerService;
    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public CustomerDTO getCustomer(@PathVariable(value = "userId") long userId){
        Customer customer = customerService.getCustomer(userId);
        return convertToDTO(customer);
    }

    private CustomerDTO convertToDTO(Customer customer){
        //dto.setBookingList(ObjectMapperUtils.mapAll(customer.getBookingList(), BookingDTO.class));
        return modelMapper.map(customer,CustomerDTO.class);
    }
}