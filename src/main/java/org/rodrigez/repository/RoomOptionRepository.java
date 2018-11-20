package org.rodrigez.repository;

import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.domain.RoomOption;
import org.rodrigez.model.ids.RoomOptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomOptionRepository extends JpaRepository<RoomOption, RoomOptionId> {
    Optional<RoomOption> findByRoomAndOptionType(Room room, OptionType optionType);
}
