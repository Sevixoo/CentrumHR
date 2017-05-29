package com.centrumhr.application.shedule;

import com.centrumhr.domain.attendance.ICalendarService;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Seweryn on 15.02.2017.
 */
public class CalendarService implements ICalendarService {

    @Override
    public boolean isWorkingDay(Date day) {
        return !isSunday(day);
    }

    @Override
    public int getNumOfDaysInMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Date dateOfDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,day+1);
        return cal.getTime();
    }

    @Override
    public boolean isSunday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    @Override
    public int dayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK)-1;
    }
}
