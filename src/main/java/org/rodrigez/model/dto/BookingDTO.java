package org.rodrigez.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO implements Serializable {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final long serialVersionUID = 1L;

    private long id;
    private long customerId;
    private String customerName;
    private long roomId;
    private long roomCategoryId;
    private String roomCategoryDescription;
    private int roomNumber;
    private String fromDate;
    private String untilDate;
    private List<BookingOptionDTO> optionList;
    private int roomPrice;
    private int summaryPrice;

    public void setFromDate(Date fromDate){
        this.fromDate = dateFormat.format(fromDate);
    }

    public void setUntilDate(Date untilDate){
        this.untilDate = dateFormat.format(untilDate);
    }

    public Date getFromDate() throws ParseException {
        return dateFormat.parse(fromDate);
    }

    public Date getUntilDate() throws ParseException {
        return dateFormat.parse(untilDate);
    }
}