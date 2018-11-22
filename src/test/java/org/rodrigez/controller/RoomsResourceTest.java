package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.dto.RoomDTO;
import org.rodrigez.service.AvailabilityService;
import org.rodrigez.service.DateInterval;
import org.rodrigez.service.InventoryService;
import org.rodrigez.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RoomsResource.class, secure = false)
public class RoomsResourceTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    InventoryService inventoryService;

    @MockBean
    AvailabilityService availabilityService;

    private ObjectMapper mapper;

    private List<Room> roomList;

    private List<Booking> bookingList;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();

        roomList = new ArrayList<>();
        Room room1 = mockRoom(1);
        Room room2 = mockRoom(1);
        Room room3 = mockRoom(2);
        roomList = Arrays.asList(room1,room2,room3);

        Booking booking1 = mockBooking("2018-11-10", "2018-11-12", room1);
        Booking booking2 = mockBooking("2018-11-14","2018-11-15",room2);
        Booking booking3 = mockBooking("2018-11-11","2018-11-13",room2);
        bookingList = Arrays.asList(booking1,booking2,booking3);

    }

    private static Booking mockBooking(String from, String until, Room room) throws Exception {
        Booking booking = new Booking();
        DateInterval interval = new DateInterval(from, until);
        booking.setFrom(interval.getFrom());
        booking.setUntil(interval.getUntil());
        booking.setRoom(room);
        booking.setCustomer(new Customer());
        room.getBookingList().add(booking);
        return booking;
    }

    private static Room mockRoom(long categoryId){
        Room room = new Room();
        Category category = new Category();
        category.setId(categoryId);
        room.setCategory(category);
        return room;
    }

    @Test
    public void getRoom() throws Exception {

        int roomId = 0;

        when(this.inventoryService.getRoom(0)).thenReturn(roomList.get(0));

        RoomDTO roomDTO = new RoomDTO(roomList.get(roomId));
        ApiResponse response = new ApiResponse(Status.OK, roomDTO);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                get("/rooms/" + roomId).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected));
    }

    @Test
    public void getRooms_InCategory() throws Exception {

        long categoryId = 1;
        List<Room> filtered = Arrays.asList(roomList.get(0),roomList.get(1));

        when(this.availabilityService.getAvailabilityRooms(categoryId,null,null)).
                thenReturn(filtered);

        List<RoomDTO> dtoList = filtered.stream().map(RoomDTO::new).collect(Collectors.toList());
        ApiResponse response = new ApiResponse(Status.OK, dtoList);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                get("/rooms?categoryId=" + categoryId).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected));
    }

    @Test
    public void getAvailableRooms_InInterval() throws Exception {

        String from = "2018-13-11";
        String until = "2018-14-11";

        List<Room> filtered = Arrays.asList(roomList.get(1),roomList.get(2));

        when(this.availabilityService.getAvailabilityRooms(null,from,until)).
                thenReturn(filtered);

        List<RoomDTO> dtoList = filtered.stream().map(RoomDTO::new).collect(Collectors.toList());
        ApiResponse response = new ApiResponse(Status.OK, dtoList);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                get("/rooms?from="+from+"&until="+until).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected));
    }

    @Test
    public void getAvailableRooms() throws Exception {

        String from = "2018-13-11";
        String until = "2018-14-11";
        long categoryId = 1;

        List<Room> filtered = Collections.singletonList(roomList.get(1));

        when(this.availabilityService.getAvailabilityRooms(categoryId,from,until)).
                thenReturn(filtered);

        List<RoomDTO> dtoList = filtered.stream().map(RoomDTO::new).collect(Collectors.toList());
        ApiResponse response = new ApiResponse(Status.OK, dtoList);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                get("/rooms?categoryId=" + categoryId+"&from="+from+"&until="+until).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected));
    }
}