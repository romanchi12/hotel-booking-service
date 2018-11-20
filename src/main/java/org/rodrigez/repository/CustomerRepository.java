package org.rodrigez.repository;

import org.rodrigez.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Override
    Optional<Customer> findById(Long id);
}
