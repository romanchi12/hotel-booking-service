package org.rodrigez.service.impl;

import org.rodrigez.model.domain.Customer;
import org.rodrigez.repository.CustomerRepository;
import org.rodrigez.service.CustomerService;
import org.rodrigez.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(long userId) {
        return customerRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("Invalid userId " + userId));
    }

    @Override
    public Customer add(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }
}