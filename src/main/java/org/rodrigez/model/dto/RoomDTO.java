package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.domain.RoomOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoomDTO implements Serializable {

    private static final long serialVersionUID = 2L;

    private long id;
    private long categoryId;
    private String categoryDescription;
    private int number;
    private List<RoomOptionDTO> optionList = new ArrayList<>();
    private List<BookingDTO> bookingList = new ArrayList<>();
    private int currentPrice;

    public RoomDTO(Room room) {
        id = room.getId();
        categoryId = room.getCategory().getId();
        categoryDescription = room.getCategory().getDescription();
        number = room.getNumber();
        for(RoomOption option: room.getOptionList()){
            RoomOptionDTO optionDTO = new RoomOptionDTO(option);
            optionList.add(optionDTO);
        }
        for(Booking booking: room.getBookingList()){
            BookingDTO bookingDTO = new BookingDTO(booking);
            bookingList.add(bookingDTO);
        }
        currentPrice = room.getCurrentPrice();
    }
}