package org.rodrigez.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomersResource.class, secure = false)
class CustomersResourceTest {

    @Test
    void testMapper(){
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Mykola");
    }

}