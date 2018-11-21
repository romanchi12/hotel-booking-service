package org.rodrigez.service.impl;

import org.rodrigez.model.domain.Category;
import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.domain.RoomOption;
import org.rodrigez.repository.*;
import org.rodrigez.service.InventoryService;
import org.rodrigez.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OptionTypeRepository optionTypeRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomOptionRepository roomOptionRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(long categoryId) throws NotFoundException {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException("Invalid categoryId" + categoryId));
    }

    @Override
    public List<OptionType> getOptionTypes() {
        return optionTypeRepository.findAll();
    }

    @Override
    public OptionType getOptionType(long optionTypeId) throws NotFoundException {
        return optionTypeRepository.findById(optionTypeId).orElseThrow(
                () -> new NotFoundException("Invalid optionTypeId " + optionTypeId));
    }

    @Override
    public RoomOption getRoomOption(long roomId, long optionTypeId) throws NotFoundException {
        Room room = getRoom(roomId);
        OptionType optionType = getOptionType(optionTypeId);
        return roomOptionRepository.findByRoomAndOptionType(room, optionType).orElseThrow(
                () -> new NotFoundException("Invalid roomOption, roomId " + roomId + ", optionTypeId " + optionTypeId));
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(long roomId) throws NotFoundException {
        return roomRepository.findById(roomId).orElseThrow(
                () -> new NotFoundException("Not Found. Invalid roomId " + roomId));
    }

    @Override
    public List<Room> getRoomsByCategoryId(long categoryId) {
        return roomRepository.findAllByCategory_Id(categoryId).orElseThrow(
                () -> new NotFoundException("Invalid categoryId " + categoryId));
    }
}