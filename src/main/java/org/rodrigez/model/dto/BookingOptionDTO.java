package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.rodrigez.model.domain.BookingOption;
import org.rodrigez.model.domain.OptionType;

@Getter
@Setter
public class BookingOptionDTO {

    private static final long serialVersionUID = 6L;

    private long optionTypeId;
    private String optionTypeDescription;
    private int price;

    public BookingOptionDTO() {
    }

    public BookingOptionDTO(BookingOption option) {
        optionTypeId = option.getOptionType().getId();
        optionTypeDescription = option.getOptionType().getDescription();
        price = option.getPrice();
    }

    public BookingOption toEntity() {
        BookingOption option = new BookingOption();

        OptionType optionType = new OptionType();
        optionType.setId(optionTypeId);
        optionType.setDescription(optionTypeDescription);
        option.setOptionType(optionType);

        option.setPrice(price);

        return option;
    }
}