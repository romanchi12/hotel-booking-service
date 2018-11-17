package org.rodrigez.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRange {
    private Date from;
    private Date until;

    public DateRange (String fromString, String untilString){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            from=dateFormat.parse(fromString);
            until=dateFormat.parse(untilString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getFrom() {
        return from;
    }

    public Date getUntil() {
        return until;
    }

    public boolean isInRange(Date date){
        return (from.compareTo(date) < 0 && until.compareTo(date) > 0);
    }
}
