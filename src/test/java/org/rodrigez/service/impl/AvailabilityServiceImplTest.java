package org.rodrigez.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.domain.Room;
import org.rodrigez.service.AvailabilityService;
import org.rodrigez.service.DateInterval;
import org.rodrigez.service.InventoryService;
import org.rodrigez.service.exceptions.DateIntervalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvailabilityServiceImplTest {

    @Autowired
    AvailabilityService availabilityService;

    @Autowired
    InventoryService inventoryService;

    private Room room;

    @Before
    public void init() throws Exception {
        room = new Room();
        room.setCategory(Mockito.mock(Category.class));
        room.setOptionList(new ArrayList<>());

        Booking booking = new Booking();
        booking.setCustomer(Mockito.mock(Customer.class));
        DateInterval interval = new DateInterval("2018-11-11","2018-11-13");
        booking.setFrom(interval.getFrom());
        booking.setUntil(interval.getUntil());
        booking.setOptionList(new ArrayList<>());

        room.setBookingList(Collections.singletonList(booking));

    }

    @Test
    public void getAvailabilityRooms() {

    }

    @Test
    public void isAvailableRoom() throws Exception {

        DateInterval interval1 = new DateInterval("2018-11-10","2018-11-11");
        boolean b1 = availabilityService.isAvailableRoom(room,interval1);
        assertTrue(b1);

        DateInterval interval2 = new DateInterval("2018-11-10","2018-11-12");
        boolean b2 = availabilityService.isAvailableRoom(room,interval2);
        assertFalse(b2);

        DateInterval interval3 = new DateInterval("2018-11-10","2018-11-15");
        boolean b3 = availabilityService.isAvailableRoom(room,interval3);
        assertFalse(b3);

        DateInterval interval4 = new DateInterval("2018-11-13","2018-11-15");
        boolean b4 = availabilityService.isAvailableRoom(room,interval4);
        assertTrue(b4);
    }
}