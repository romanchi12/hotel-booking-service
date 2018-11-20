package org.rodrigez.util;

import lombok.Getter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
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

    public static long daysInRange(Date d1, Date d2){
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }



    public boolean isInRange(Date date){
        return (from.compareTo(date) < 0 && until.compareTo(date) > 0);
    }
}
