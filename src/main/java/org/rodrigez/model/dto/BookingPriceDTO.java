package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.BookingOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingPriceDTO implements Serializable {

    private static final long serialVersionUID = 8L;

    private long id;
    private int roomPrice;
    private List<BookingOptionDTO> optionList = new ArrayList<>();
    private int summaryPrice;

    public BookingPriceDTO(Booking booking) {
        id = booking.getId();
        roomPrice = booking.getRoomPrice();
        summaryPrice = booking.getSummaryPrice();
        for(BookingOption option: booking.getOptionList()){
            BookingOptionDTO optionDTO = new BookingOptionDTO(option);
            optionList.add(optionDTO);
        }
    }
}
