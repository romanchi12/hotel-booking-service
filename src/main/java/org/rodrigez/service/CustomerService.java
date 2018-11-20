package org.rodrigez.service;

import org.rodrigez.model.domain.Customer;

public interface CustomerService {
    Customer getCustomer(long userId);
    Customer add(Customer customer);
}
