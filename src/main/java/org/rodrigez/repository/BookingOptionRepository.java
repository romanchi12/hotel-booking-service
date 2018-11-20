package org.rodrigez.repository;

import org.rodrigez.model.domain.BookingOption;
import org.rodrigez.model.ids.BookingOptionId;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingOptionRepository extends JpaRepository<BookingOption, BookingOptionId> {
}
