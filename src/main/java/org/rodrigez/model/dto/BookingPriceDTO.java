package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingPriceDTO implements Serializable {

    private static final long serialVersionUID = 8L;

    private long id;
    private int roomPrice;
    private List<BookingOptionDTO> optionList;
    private int summaryPrice;
}
