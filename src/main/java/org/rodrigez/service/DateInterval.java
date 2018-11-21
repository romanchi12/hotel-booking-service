package org.rodrigez.service;

import org.rodrigez.service.exceptions.DateIntervalException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInterval {

    private Date from;
    private Date until;

    public DateInterval(String fromString, String untilString) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        from = dateFormat.parse(fromString);
        until = dateFormat.parse(untilString);
        if(from.after(until)) throw new DateIntervalException("From before until");
    }

    public Date getFrom() {
        return from;
    }

    public Date getUntil() {
        return until;
    }

    public DateInterval(Date intervalFrom, Date until) throws Exception {
        this.from = intervalFrom;
        this.until = until;
        if(from.after(until)) throw new DateIntervalException("From before until");
    }

    public boolean overlaps(DateInterval checked){
        boolean checkedFromBeforeIntervalUntil = checked.getFrom().compareTo(until)<0;
        boolean checkedUntilAfterIntervalFrom = checked.getUntil().compareTo(from)>0;
        return checkedFromBeforeIntervalUntil && checkedUntilAfterIntervalFrom;
    }

}