package org.rodrigez.repository;

import org.rodrigez.model.domain.BookingOption;
import org.rodrigez.model.ids.BookingOptionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingOptionRepository extends JpaRepository<BookingOption, BookingOptionId> {
}
