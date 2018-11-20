package org.rodrigez.service.impl;

import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Room;
import org.rodrigez.repository.RoomRepository;
import org.rodrigez.service.AvailabilityService;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    InventoryService inventoryService;

    @Override
    public List<Room> getAvailabilityRooms(Long categoryId, String from, String until) throws ParseException {

        List<Room> filtered = inventoryService.getRooms();

        if(categoryId!=null){
            List<Room> roomsInCategory = inventoryService.getRoomsByCategoryId(categoryId);
            filtered.retainAll(roomsInCategory);
        }

        if(from!=null&&until!=null){
            List<Room> availableRooms = getAvailableRooms(from,until);
            filtered.retainAll(availableRooms);
        }

        return filtered;
    }

    private List<Room> getAvailableRooms(String from, String until) throws ParseException {
        DateInterval interval = new DateInterval(from,until);
        List<Room> available = new ArrayList<>();
        for(Room room: inventoryService.getRooms()){
            if(isAvailableRoom(room, interval)){
                available.add(room);
            }
        }
        return available;
    }

    private boolean isAvailableRoom(Room room, DateInterval dateInterval){
        List<Booking> bookingList = room.getBookingList();
        for(Booking booking : bookingList){
            if(dateInterval.overlaps(booking)){
                return false;
            }
        }
        return true;
    }

    private class DateInterval {

        private Date intervalFrom;
        private Date intervalUntil;

        DateInterval(String fromString, String untilString) throws ParseException {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            intervalFrom = dateFormat.parse(fromString);
            intervalUntil = dateFormat.parse(untilString);
        }

        boolean overlaps(Booking booking){
            Date bookingFrom = booking.getFrom();
            Date bookingUntil = booking.getUntil();
            return this.isInInterval(bookingFrom) && this.isInInterval(bookingUntil);
        }

        boolean isInInterval(Date date){
            return (intervalFrom.compareTo(date) >= 0 || intervalUntil.compareTo(date) <= 0);
        }

    }
}