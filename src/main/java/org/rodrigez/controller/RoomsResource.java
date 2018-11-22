package org.rodrigez.controller;

import org.rodrigez.model.domain.Room;
import org.rodrigez.model.dto.RoomDTO;
import org.rodrigez.service.AvailabilityService;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getRoom(@PathVariable("roomId") long roomId){

        Room room = inventoryService.getRoom(roomId);
        RoomDTO roomDTO = new RoomDTO(room);
        return ResponseEntity.ok(roomDTO);
    }

    @GetMapping
    public ResponseEntity getFilteredRooms(
            @RequestParam(value = "categoryId",required = false) Long categoryId,
            @RequestParam(value = "from",required = false) String from,
            @RequestParam(value = "until", required = false) String until) throws Exception {

        List<Room> entityList = availabilityService.getAvailabilityRooms(categoryId,from,until);
        List<RoomDTO> dtoList = entityList.stream().map(RoomDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}