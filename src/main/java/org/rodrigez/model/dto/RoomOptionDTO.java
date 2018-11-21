package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.rodrigez.model.domain.RoomOption;

@Getter
@Setter
public class RoomOptionDTO {

    private static final long serialVersionUID = 7L;

    private long roomId;
    private long optionTypeId;
    private String optionTypeDescription;
    private int price;

    public RoomOptionDTO(RoomOption option) {
        roomId = option.getRoom().getId();
        optionTypeId = option.getOptionType().getId();
        optionTypeDescription = option.getOptionType().getDescription();
        price = option.getPrice();
    }
}
