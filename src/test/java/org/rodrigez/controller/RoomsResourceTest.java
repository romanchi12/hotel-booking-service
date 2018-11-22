package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.dto.RoomDTO;
import org.rodrigez.service.AvailabilityService;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RoomsResource.class, secure = false)
public class RoomsResourceTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    InventoryService inventoryService;

    @MockBean
    AvailabilityService availabilityService;

    private ObjectMapper mapper = new ObjectMapper();

    private static Room mockRoom(){
        Room room = new Room();
        room.setCategory(new Category());
        return room;
    }

    @Test
    public void getRoom() throws Exception {

        int roomId = 0;

        Room room = mockRoom();
        RoomDTO roomDTO = new RoomDTO(room);

        when(this.inventoryService.getRoom(roomId)).thenReturn(room);

        String expected = mapper.writeValueAsString(roomDTO);

        this.mvc.perform(
                get("/rooms/" + roomId).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected));
    }

    @Test
    public void getRooms_InCategory() throws Exception {

        long categoryId = 1;

        List<Room> filtered = Collections.singletonList(mockRoom());
        List<RoomDTO> dtoList = filtered.stream().map(RoomDTO::new).collect(Collectors.toList());

        when(this.availabilityService.getAvailabilityRooms(categoryId,null,null)).thenReturn(filtered);

        String expected = mapper.writeValueAsString(dtoList);

        this.mvc.perform(
                get("/rooms?categoryId=" + categoryId).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected)
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void getAvailableRooms_InInterval() throws Exception {

        String from = "2018-13-11";
        String until = "2018-14-11";

        List<Room> filtered = Collections.singletonList(mockRoom());
        List<RoomDTO> dtoList = filtered.stream().map(RoomDTO::new).collect(Collectors.toList());

        when(this.availabilityService.getAvailabilityRooms(null,from,until)).
                thenReturn(filtered);


        String expected = mapper.writeValueAsString(dtoList);

        this.mvc.perform(
                get("/rooms?from="+from+"&until="+until).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected)
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void getAvailableRooms() throws Exception {

        String from = "2018-13-11";
        String until = "2018-14-11";
        long categoryId = 1;

        List<Room> filtered = Collections.singletonList(mockRoom());
        List<RoomDTO> dtoList = filtered.stream().map(RoomDTO::new).collect(Collectors.toList());

        when(this.availabilityService.getAvailabilityRooms(categoryId,from,until)).thenReturn(filtered);

        String expected = mapper.writeValueAsString(dtoList);

        this.mvc.perform(
                get("/rooms?categoryId=" + categoryId+"&from="+from+"&until="+until).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected)
        ).andExpect(
                status().isOk()
        );
    }
}