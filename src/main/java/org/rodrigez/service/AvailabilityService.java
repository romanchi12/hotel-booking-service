package org.rodrigez.service;

import org.rodrigez.model.domain.Room;

import java.text.ParseException;
import java.util.List;

public interface AvailabilityService {
    List<Room> getAvailabilityRooms(Long categoryId, String from, String until) throws ParseException;
}