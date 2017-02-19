package com.centrumhr.domain.attendance.validation;

import com.centrumhr.domain.attendance.AttendanceDay;
import com.centrumhr.domain.attendance.AttendanceEmployee;
import com.centrumhr.domain.attendance.ICalendarService;

/**
 * Created by Seweryn on 08.02.2017.
 */
public abstract class DayValidator {

    private String errorMessage;

    public DayValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public abstract boolean appliesFor(AttendanceEmployee attendanceEmployee);

    public abstract boolean isValid(AttendanceDay day , AttendanceDay[] days , ICalendarService calendarService);

}
