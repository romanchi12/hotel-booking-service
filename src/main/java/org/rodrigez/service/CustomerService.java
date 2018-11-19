package org.rodrigez.service;

import org.rodrigez.model.domain.Customer;
import org.rodrigez.validation.UserRequest;

public interface CustomerService {
    Customer add(UserRequest request);
    Customer getCustomer(long userId);
}
