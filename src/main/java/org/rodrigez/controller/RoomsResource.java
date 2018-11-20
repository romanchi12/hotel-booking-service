package org.rodrigez.controller;

import org.modelmapper.ModelMapper;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.dto.RoomDTO;
import org.rodrigez.service.InventoryService;
import org.rodrigez.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @GetMapping(value = "/{roomId}")
    public ApiResponse getRoom(@PathVariable("roomId") long roomId){
        try {
            Room room = inventoryService.getRoom(roomId);
            return new ApiResponse(Status.OK,convertToDTO(room));
        } catch (Exception e){
            return new ApiResponse(Status.ERROR,e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse getFilteredRooms(
            @RequestParam(value = "categoryId",required = false) Long categoryId,
            @RequestParam(value = "from",required = false) String from,
            @RequestParam(value = "until", required = false) String until){

        try {
            List<Room> entityList = availabilityService.getAvailabilityRooms(categoryId,from,until);
            List<RoomDTO> dtoList = entityList.stream().map(this::convertToDTO).collect(Collectors.toList());
            return new ApiResponse(Status.OK, dtoList);
        } catch (Exception e) {
            return new ApiResponse(Status.ERROR, e.getMessage());
        }

    }

    private RoomDTO convertToDTO(Room room){
        return modelMapper.map(room, RoomDTO.class);
    }
}