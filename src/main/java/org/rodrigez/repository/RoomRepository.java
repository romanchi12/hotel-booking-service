package org.rodrigez.repository;

import org.rodrigez.model.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<List<Room>> findAllByCategory_Id(long categoryId);
    Optional<Room> findById(long roomId);
}
