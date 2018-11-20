package org.rodrigez.service;

import org.rodrigez.model.domain.Category;
import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.domain.RoomOption;

import java.util.List;

public interface InventoryService {
    List<Category> getCategories();
    Category getCategory(long categoryId);
    List<OptionType> getOptionTypes();
    OptionType getOptionType(long optionTypeId);
    List<RoomOption> getRoomOptions(long roomId, List<Long> optionIdList);
    RoomOption getRoomOption(long roomId, long optionTypeId);
    List<Room> getRooms();
    Room getRoom(long roomId);
}
