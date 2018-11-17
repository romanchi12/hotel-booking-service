package org.rodrigez.repository;

import org.rodrigez.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByCategory_CategoryId(long categoryId);
}
