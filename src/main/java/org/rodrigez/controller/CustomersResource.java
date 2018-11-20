package org.rodrigez.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
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

    @GetMapping(value = "/{userId}")
    public ApiResponse getCustomer(@PathVariable(value = "userId") long userId){
        try {
            Customer customer = customerService.getCustomer(userId);
            return new ApiResponse(Status.OK, convertToDTO(customer));
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, e.getMessage());
        }
    }

    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDTO dto){
        Customer customer = convertToEntity(dto);
        customerService.add(customer);
        return new ApiResponse(Status.OK, convertToDTO(customer));
    }

    private CustomerDTO convertToDTO(Customer customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }

    private Customer convertToEntity(CustomerDTO dto){
        return modelMapper.map(dto, Customer.class);
    }
}