package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingOptionDTO {

    private static final long serialVersionUID = 6L;

    private long optionTypeId;
    private String optionTypeDescription;
    private int price;
}