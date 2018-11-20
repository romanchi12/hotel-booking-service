package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;
import java.util.TimeZone;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO implements Serializable {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Kiev");
        dateFormat.setTimeZone(timeZone);
    }

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

    public Date getFromDate(){
        LocalDate localDate = LocalDate.parse(fromDate, dateTimeFormatter);
        return Date.valueOf(localDate);
    }

    public Date getUntilDate(){
        LocalDate localDate = LocalDate.parse(untilDate, dateTimeFormatter);
        return Date.valueOf(localDate);
    }
}