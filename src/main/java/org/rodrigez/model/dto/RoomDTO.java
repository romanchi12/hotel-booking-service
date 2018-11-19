package org.rodrigez.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<RoomOptionDTO> roomOptionList;
    @JsonManagedReference
    private List<BookingDTO> bookingList;
    private int currentPrice;
}