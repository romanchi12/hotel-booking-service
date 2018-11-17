package org.rodrigez.controller;

import org.rodrigez.model.Room;
import org.rodrigez.model.dto.RoomDTO;
import org.rodrigez.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
public class RoomsResource {
    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    public RoomDTO getRoom(@PathVariable("roomId") long roomId){
        Room room = roomService.getRoom(roomId);
        return new RoomDTO(room);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RoomDTO> getFilteredRooms(
            @RequestParam(value = "categoryId",required = false) Long categoryId,
            @RequestParam(value = "from",required = false) String from,
            @RequestParam(value = "until", required = false) String until){

        List<Room> entityList = roomService.getFilteredRooms(categoryId,from,until);
        return entityList.stream().map(RoomDTO::new).collect(Collectors.toList());
    }
}