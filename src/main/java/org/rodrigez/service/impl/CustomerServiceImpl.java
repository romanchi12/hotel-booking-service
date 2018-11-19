package org.rodrigez.service.impl;

import org.rodrigez.model.domain.Customer;
import org.rodrigez.repository.CustomerRepository;
import org.rodrigez.service.CustomerService;
import org.rodrigez.validation.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer add(UserRequest request) {
        Customer customer = create(request);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer getCustomer(long userId) {
        return customerRepository.getOne(userId);
    }

    private Customer create(UserRequest userRequest){

        Customer customer = new Customer();
        customer.setName(userRequest.getName());

        return customer;
    }
}