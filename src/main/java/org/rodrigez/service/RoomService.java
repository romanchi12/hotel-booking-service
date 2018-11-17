package org.rodrigez.service;

import org.rodrigez.model.Room;
import java.util.List;

public interface RoomService {
    List<Room> getRooms();
    Room getRoom(long roomId);
    List<Room> getFilteredRooms(Long categoryId, String from, String until);
}
