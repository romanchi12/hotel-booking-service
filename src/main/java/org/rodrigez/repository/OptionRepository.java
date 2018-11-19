package org.rodrigez.repository;

import org.rodrigez.model.domain.OptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<OptionType, Long> {
}
