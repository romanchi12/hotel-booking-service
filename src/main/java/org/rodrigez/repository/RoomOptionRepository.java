package org.rodrigez.repository;

import org.rodrigez.model.domain.RoomOption;
import org.rodrigez.model.ids.RoomOptionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomOptionRepository extends JpaRepository<RoomOption, RoomOptionId> {
}
