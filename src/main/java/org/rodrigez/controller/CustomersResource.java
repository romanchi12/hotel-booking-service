package org.rodrigez.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.dto.CustomerDTO;
import org.rodrigez.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomersResource {
    @Autowired
    CustomerService customerService;
    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public CustomerDTO getCustomer(@PathVariable(value = "userId") long userId){
        Customer customer = customerService.getCustomer(userId);
        return convertToDTO(customer);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CustomerDTO addCustomer(@RequestBody CustomerDTO dto){
        Customer customer = convertToEntity(dto);
        customerService.add(customer);
        return convertToDTO(customer);
    }

    private CustomerDTO convertToDTO(Customer customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }

    private Customer convertToEntity(CustomerDTO dto){

        return modelMapper.map(dto, Customer.class);
    }
}