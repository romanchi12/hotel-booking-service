package org.rodrigez.controller;

import org.modelmapper.ModelMapper;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.dto.RoomDTO;
import org.rodrigez.service.InventoryService;
import org.rodrigez.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
public class RoomsResource {
    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    public RoomDTO getRoom(@PathVariable("roomId") long roomId){
        Room room = inventoryService.getRoom(roomId);
        return convertToDTO(room);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RoomDTO> getFilteredRooms(
            @RequestParam(value = "categoryId",required = false) Long categoryId,
            @RequestParam(value = "from",required = false) String from,
            @RequestParam(value = "until", required = false) String until){

        List<Room> entityList = availabilityService.getAvailabilityRooms(categoryId,from,until);
        return entityList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RoomDTO convertToDTO(Room room){
        return modelMapper.map(room, RoomDTO.class);
    }
}