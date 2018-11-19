package org.rodrigez.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long customerId;
    private String customerName;
    private long roomId;
    private long roomCategoryId;
    private String roomCategoryDescription;
    private int roomNumber;
    private Date from;
    private Date until;
    @JsonManagedReference
    private List<BookingOptionDTO> bookingOptionList;
    private int roomPrice;
    private int summaryPrice;
}