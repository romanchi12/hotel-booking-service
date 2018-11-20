package org.rodrigez.repository;

import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Override
    Optional<Booking> findById(Long id);
    List<Booking> findAllByCustomer(Customer customer);
}
