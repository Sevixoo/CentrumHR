package com.centrumhr.domain.attendance.validation;

import com.centrumhr.domain.attendance.AttendanceDay;
import com.centrumhr.domain.attendance.AttendanceEmployee;
import com.centrumhr.domain.attendance.ICalendarService;

/**
 * Created by Seweryn on 08.02.2017.
 */
public class PregnantCantWorkMoreThan4HoursValidationRule extends DayValidator {

    public PregnantCantWorkMoreThan4HoursValidationRule(String errorMessage) {
        super("PREGNANT_CANT_WORK_MORE_THAN_4_HOURS_ERROR");
    }

    public boolean appliesFor(AttendanceEmployee attendanceEmployee) {
        return attendanceEmployee.isPregnant();
    }

    public boolean isValid(AttendanceDay day, AttendanceDay[] days, ICalendarService calendarService) {
        return day.workScheduled() && day.workDuration() > 4*60;
    }
}
