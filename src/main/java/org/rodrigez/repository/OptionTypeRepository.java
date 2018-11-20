package org.rodrigez.repository;

import org.rodrigez.model.domain.OptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionTypeRepository extends JpaRepository<OptionType, Long> {
    Optional<OptionType> findById(long id);
}
