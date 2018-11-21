package org.rodrigez.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.repository.CustomerRepository;
import org.rodrigez.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void add() {
        Customer customer = new Customer();
        customer.setName("Mykola");
        customer = this.customerService.add(customer);
        Customer result = this.customerService.getCustomer(customer.getId());
        System.out.println(customer);
        System.out.println(result);
    }
}