package org.rodrigez.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest{

    private long customerId;
    private long roomId;
    private String from;
    private String until;
    private List<Long> optionList;
}
