package org.rodrigez.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomersResource.class, secure = false)
class CustomersResourceTest {
    @Autowired
    ModelMapper modelMapper;

    private CustomerDTO convertToDTO(Customer customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }

    @Test
    void testMap(){
        TypeMap typeMap = modelMapper.createTypeMap(
                Customer.class,
                CustomerDTO.class);
        modelMapper.validate();
    }

    @Test
    void testMapper(){
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Mykola");
        CustomerDTO dto = convertToDTO(customer);
        assertEquals(customer.getId(),dto.getId());
        assertEquals(customer.getName(),dto.getName());
    }

}