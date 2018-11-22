package org.rodrigez.service;

import org.rodrigez.model.domain.Room;

import java.util.List;

public interface AvailabilityService {
    List<Room> getAvailabilityRooms(Long categoryId, String from, String until) throws Exception;

    List<Room> getAvailableRooms(String from, String until) throws Exception;

    boolean isAvailableRoom(Room room, DateInterval dateInterval) throws Exception;
}