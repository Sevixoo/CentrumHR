package com.centrumhr.domain.attendance;

import com.centrumhr.domain.attendance.ICalendarService;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Seweryn on 28.05.2017.
 */
public class MockCalendarService implements ICalendarService {

    @Override
    public boolean isWorkingDay(Date day) {
        return !isSunday(day);
    }

    @Override
    public int getNumOfDaysInMonth(Date month) {
        return 30;
    }

    @Override
    public Date dateOfDay(Date month, int i) {
        return null;
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
