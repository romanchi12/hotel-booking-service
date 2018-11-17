package org.rodrigez.model.dto;

import org.rodrigez.model.Booking;
import org.rodrigez.model.Option;
import org.rodrigez.validation.BookingRequest;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BookingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long bookingId;
    private String userName;
    private long roomId;
    private Date fromDate;
    private Date untilDate;
    private List<Long> optionIdList;
    private int price;

    public BookingDTO(Booking booking){
        bookingId = booking.getBookingId();
        userName=booking.getUser().getName();
        roomId=booking.getRoom().getRoomId();
        fromDate=booking.getFrom();
        untilDate=booking.getUntil();
        optionIdList = new LinkedList<>();
        for(Option option: booking.getOptionList()){
            optionIdList.add(option.getOptionId());
        }
        price=booking.getPrice();
    }

    public BookingDTO(BookingRequest request){
        userName=request.getUsername();
        roomId= request.getRoomId();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fromDate=dateFormat.parse(request.getFrom());
            untilDate=dateFormat.parse(request.getUntil());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Long> optionIdList = new LinkedList<>();
        String[] optionIdArray = request.getOptionIdList().split(",");
        for(String optionId: optionIdArray){
            optionIdList.add(Long.valueOf(optionId));
        }
        this.optionIdList=optionIdList;
        price=request.getPrice();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getBookingId() {
        return bookingId;
    }

    public String getUserName() {
        return userName;
    }

    public long getRoomId() {
        return roomId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public List<Long> getOptionIdList() {
        return optionIdList;
    }

    public int getPrice() {
        return price;
    }
}