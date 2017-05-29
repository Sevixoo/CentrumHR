package com.centrumhr.domain.attendance;

import java.util.Date;

/**
 * Created by Seweryn on 07.02.2017.
 */
public interface ICalendarService {

    boolean isWorkingDay(Date day);

    int getNumOfDaysInMonth( Date month );

    Date dateOfDay(Date month, int i);

    boolean isSunday(Date date);

    int dayOfWeek(Date date);
}
