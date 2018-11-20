package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RoomDTO implements Serializable {

    private static final long serialVersionUID = 2L;

    private long id;
    private long categoryId;
    private String categoryDescription;
    private int number;
    private List<RoomOptionDTO> optionList;
    private List<BookingDTO> bookingList;
    private int currentPrice;
}