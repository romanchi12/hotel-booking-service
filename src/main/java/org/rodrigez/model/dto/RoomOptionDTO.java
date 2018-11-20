package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomOptionDTO {

    private static final long serialVersionUID = 7L;

    private long roomId;
    private long optionTypeId;
    private String optionTypeDescription;
    private int price;
}
