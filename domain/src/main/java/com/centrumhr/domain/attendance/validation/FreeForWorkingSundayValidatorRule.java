package com.centrumhr.domain.attendance.validation;

import com.centrumhr.domain.attendance.AttendanceDay;
import com.centrumhr.domain.attendance.AttendanceEmployee;
import com.centrumhr.domain.attendance.ICalendarService;

/**
 * Created by Seweryn on 08.02.2017.
 */
public class FreeForWorkingSundayValidatorRule extends DayValidator {

    public FreeForWorkingSundayValidatorRule(String errorMessage) {
        super("FREE_FOR_WORKING_SUNDAY_ERROR");
    }

    public boolean appliesFor(AttendanceEmployee attendanceEmployee) {
        return true;
    }

    public boolean isValid(AttendanceDay day, AttendanceDay[] days , ICalendarService calendarService) {
        return !calendarService.isSunday(day.getDate()) || hasFreeDayNextWeek(day,days,calendarService);
    }

    private boolean hasFreeDayNextWeek(AttendanceDay day, AttendanceDay[] days,ICalendarService calendarService){
        int counter = 0;
        for (int i = 0; i < days.length; i++) {
            AttendanceDay d = days[i];
            if(d.equals(day)){
                counter = 1;
            }else if(counter == 1 && (!calendarService.isWorkingDay(d.getDate()) || d.workScheduled()) ){
                counter++;
            }
            if(counter>=7){
                return false;
            }
        }
        return true;
    }

}
