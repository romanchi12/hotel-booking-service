package org.rodrigez.repository;

import org.rodrigez.model.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Override
    Optional<Booking> findById(Long id);
}
