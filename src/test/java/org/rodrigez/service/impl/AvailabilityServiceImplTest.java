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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvailabilityServiceImplTest {

    @Autowired
    AvailabilityService availabilityService;

    @MockBean
    InventoryService inventoryService;

    private Room room1;
    private Room room2;

    @Before
    public void init() throws Exception {
        room1 = mockRoom(1L);
        room2 = mockRoom(2L);

        mockBooking("2018-11-11","2018-11-13", room1);
        mockBooking("2018-11-12","2018-11-13", room2);
        mockBooking("2018-11-14","2018-11-15", room2);
    }

    private void mockBooking(String from, String until, Room room) throws Exception {
        Booking booking = new Booking();
        booking.setCustomer(Mockito.mock(Customer.class));
        DateInterval interval = new DateInterval(from,until);
        booking.setFrom(interval.getFrom());
        booking.setUntil(interval.getUntil());
        booking.setOptionList(new ArrayList<>());
        booking.setRoom(room);
        room.getBookingList().add(booking);
    }

    private Room mockRoom(long categoryId){
        Room room = new Room();
        Category category = new Category();
        category.setId(categoryId);
        room.setCategory(category);
        room.setOptionList(new ArrayList<>());
        return room;
    }

    @Test
    public void getAvailabilityRooms() throws Exception {
        List<Room> roomList = new ArrayList<>(Arrays.asList(room1,room2));
        when(this.inventoryService.getRooms()).thenReturn(roomList);
        List<Room> actual = availabilityService.getAvailabilityRooms(null, "2018-11-13","2018-11-14");
        List<Room> expected = new ArrayList<>(Arrays.asList(room1,room2));
        assertEquals(actual,expected);
    }

    @Test
    public void getAvailabilityRooms_0() throws Exception {
        List<Room> roomList = new ArrayList<>(Arrays.asList(room1,room2));
        when(this.inventoryService.getRooms()).thenReturn(roomList);
        List<Room> actual = availabilityService.getAvailabilityRooms(null, "2018-11-11","2018-11-14");
        List<Room> expected = new ArrayList<>();
        assertEquals(actual,expected);
    }

    @Test
    public void getAvailabilityRooms_1() throws Exception {
        List<Room> roomList = new ArrayList<>(Arrays.asList(room1,room2));
        when(this.inventoryService.getRooms()).thenReturn(roomList);
        List<Room> actual = availabilityService.getAvailabilityRooms(null, "2018-11-14","2018-11-15");
        List<Room> expected = Collections.singletonList(room1);
        assertEquals(actual,expected);
    }

    @Test
    public void getAvailabilityRooms_2() throws Exception {
        List<Room> roomList = new ArrayList<>(Arrays.asList(room1,room2));
        when(this.inventoryService.getRooms()).thenReturn(roomList);
        when(this.inventoryService.getRoomsByCategoryId(2L)).thenReturn(Collections.singletonList(room2));
        when(this.inventoryService.getRoomsByCategoryId(1L)).thenReturn(Collections.singletonList(room1));
        List<Room> actual = availabilityService.getAvailabilityRooms(2L, "2018-11-11","2018-11-12");
        List<Room> expected = Collections.singletonList(room2);
        assertEquals(actual,expected);
    }

    @Test
    public void getAvailabilityRooms_3() throws Exception {
        List<Room> roomList = new ArrayList<>(Arrays.asList(room1,room2));
        when(this.inventoryService.getRooms()).thenReturn(roomList);

        List<Room> actual = availabilityService.getAvailabilityRooms(null, null,null);
        List<Room> expected = new ArrayList<>(Arrays.asList(room1,room2));
        assertEquals(actual,expected);
    }


    @Test
    public void isAvailableRoom() throws Exception {
        Room room = room1;

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

    @Test
    public void getAvailableRooms() {
    }
}