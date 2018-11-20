package org.rodrigez.service.impl;

import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Room;
import org.rodrigez.repository.RoomRepository;
import org.rodrigez.service.AvailabilityService;
import org.rodrigez.service.InventoryService;
import org.rodrigez.util.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    InventoryService inventoryService;

    @Override
    public List<Room> getAvailabilityRooms(Long categoryId, String from, String until) {

        List<Room> availableRooms = getAvailableRooms(from,until);
        List<Room> roomsInCategory = getRoomsByCategoryId(categoryId);

        List<Room> filtered = inventoryService.getRooms();
        filtered.retainAll(availableRooms);
        filtered.retainAll(roomsInCategory);

        return filtered;
    }

    private List<Room> getRoomsByCategoryId(Long categoryId) {
        if(categoryId==null){
            return inventoryService.getRooms();
        }
        return roomRepository.findAllByCategory_Id(categoryId);
    }

    private List<Room> getAvailableRooms(String from, String until) {
        if(from==null||until==null){
            return inventoryService.getRooms();
        }
        DateRange dateRange = new DateRange(from,until);
        List<Room> available = new ArrayList<>();
        for(Room room: inventoryService.getRooms()){
            if(isAvailableRoom(room, dateRange)){
                available.add(room);
            }
        }
        return available;
    }

    private boolean isAvailableRoom(Room room, DateRange dateRange){
        List<Booking> bookingList = room.getBookingList();
        Date from, until;
        for(Booking booking : bookingList){
            from = booking.getFrom();
            until = booking.getUntil();
            if(dateRange.isInRange(from)||dateRange.isInRange(until)){
                return false;
            }
        }
        return true;
    }
}