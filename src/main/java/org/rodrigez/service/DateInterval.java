package org.rodrigez.service;

import org.rodrigez.model.domain.Booking;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInterval {

    private Date intervalFrom;
    private Date intervalUntil;

    public DateInterval(String fromString, String untilString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        intervalFrom = dateFormat.parse(fromString);
        intervalUntil = dateFormat.parse(untilString);
    }

    public Date getIntervalFrom() {
        return intervalFrom;
    }

    public Date getIntervalUntil() {
        return intervalUntil;
    }

    public DateInterval(Date intervalFrom, Date intervalUntil) {
        this.intervalFrom = intervalFrom;
        this.intervalUntil = intervalUntil;
    }

    public boolean overlaps(Booking booking){
        Date bookingFrom = booking.getFrom();
        Date bookingUntil = booking.getUntil();
        return this.isInInterval(bookingFrom) && this.isInInterval(bookingUntil);
    }

    boolean isInInterval(Date date){
        return (intervalFrom.compareTo(date) >= 0 || intervalUntil.compareTo(date) <= 0);
    }

}