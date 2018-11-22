package org.rodrigez.service;

import org.rodrigez.model.domain.Room;
import org.rodrigez.service.exceptions.DateIntervalException;
import org.rodrigez.service.exceptions.NotFoundException;

import java.util.List;

public interface AvailabilityService {

    /**
     * @param categoryId checked category Id from url, nullable
     * @param from  checked from date from url, nullable
     * @param until checked from date from url, nullable
     * @return list of rooms,
     *         if categoryId and dates are null - then all rooms,
     *         if categoryId is null - available for specified date,
     *         if date is null - filtered by category,
     *         if all params are specified - available and filtered
     *
     * @throws NotFoundException if category Id is invalid
     * @throws DateIntervalException if date interval is invalid
     */
    List<Room> getAvailabilityRooms(Long categoryId, String from, String until) throws Exception;

    /**
     * @return list of rooms available for interval between {@param from} and {@param until}
     * @throws DateIntervalException if date interval is invalid
     */
    List<Room> getAvailableRooms(String from, String until) throws Exception;


    /**
     * @return true if {@param room} is available for specified {@param dateInterval}
     * @throws DateIntervalException if date interval is invalid
     */
    boolean isAvailableRoom(Room room, DateInterval dateInterval) throws Exception;
}