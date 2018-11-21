package org.rodrigez.controller;

import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
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

    @GetMapping(value = "/{roomId}")
    public ApiResponse getRoom(@PathVariable("roomId") long roomId){
        try {
            Room room = inventoryService.getRoom(roomId);
            RoomDTO roomDTO = new RoomDTO(room);
            return new ApiResponse(Status.OK, roomDTO);
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
            List<RoomDTO> dtoList = entityList.stream().map(RoomDTO::new).collect(Collectors.toList());
            return new ApiResponse(Status.OK, dtoList);
        } catch (Exception e) {
            return new ApiResponse(Status.ERROR, e.getMessage());
        }

    }
}