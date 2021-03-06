package com.emcevoy.beepboop.Util;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date getMorning() {
        return getTime(8,0);
    }

    public static Date getNoon() {
        return getTime(12,0);
    }

    public static Date getAfternoon() {
        return getTime(15,0);
    }

    public static Date getEvening() {
        return getTime(18,0);
    }

    public static Date getNight() {
        return getTime(21,0);
    }

    public static Date getTime(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date toDate(String text) {
        Parser parser = new Parser();
        List<DateGroup> groups = parser.parse(text);
        Date date = null;
        for(DateGroup group:groups) {
            List<Date> dates = group.getDates();
            if(!dates.isEmpty()) {
                date = dates.get(0);
            }
        }
        return date;
    }
}
