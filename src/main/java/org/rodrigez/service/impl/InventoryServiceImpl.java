package org.rodrigez.service.impl;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.domain.RoomOption;
import org.rodrigez.model.ids.RoomOptionId;
import org.rodrigez.repository.*;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OptionRepository optionRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomOptionRepository roomOptionRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(long categoryId) {
        return categoryRepository.getOne(categoryId);
    }

    @Override
    public List<OptionType> getOptionTypes() {
        return optionRepository.findAll();
    }

    @Override
    public OptionType getOptionType(long optionId) {
        return optionRepository.getOne(optionId);
    }

    @Override
    public List<RoomOption> getRoomOptions(long roomId, List<Long> optionTypeIdList) {
        List<RoomOption> optionList = new ArrayList<>();
        for(long optionTypeId: optionTypeIdList){
            optionList.add(getRoomOption(roomId,optionTypeId));
        }
        return optionList;
    }

    @Override
    @NotFound(action = NotFoundAction.IGNORE)
    public RoomOption getRoomOption(long roomId, long optionTypeId){
        Room room = getRoom(roomId);
        OptionType optionType = getOptionType(optionTypeId);
        return roomOptionRepository.getOne(new RoomOptionId(room,optionType));
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(long roomId) {
        return roomRepository.getOne(roomId);
    }
}
