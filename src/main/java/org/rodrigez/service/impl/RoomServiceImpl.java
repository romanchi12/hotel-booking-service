package org.rodrigez.service.impl;

import org.rodrigez.model.Room;
import org.rodrigez.repository.RoomRepository;
import org.rodrigez.service.BookingService;
import org.rodrigez.service.RoomService;
import org.rodrigez.validation.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    BookingService bookingService;

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(long roomId) {
        return roomRepository.getOne(roomId);
    }

    @Override
    public List<Room> getFilteredRooms(Long categoryId, String from, String until) {

        List<Room> availableRooms = getAvailableRooms(from,until);
        List<Room> roomsInCategory = getRoomsByCategoryId(categoryId);

        List<Room> filtered = getRooms();
        filtered.retainAll(availableRooms);
        filtered.retainAll(roomsInCategory);

        return filtered;
    }

    private List<Room> getRoomsByCategoryId(Long categoryId) {
        if(categoryId==null){
            return getRooms();
        }
        return roomRepository.findAllByCategory_CategoryId(categoryId);
    }

    private List<Room> getAvailableRooms(String from, String until) {
        if(from==null||until==null){
            return getRooms();
        }
        DateRange dateRange = new DateRange(from,until);
        List<Room> available = new ArrayList<>();
        for(Room room: getRooms()){
            if(bookingService.isAvailableRoom(room, dateRange)){
                available.add(room);
            }
        }
        return available;
    }
}