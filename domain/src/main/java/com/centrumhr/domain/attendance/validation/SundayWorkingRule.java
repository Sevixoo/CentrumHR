package com.centrumhr.domain.attendance.validation;

import com.centrumhr.domain.attendance.*;
import com.centrumhr.domain.common.DateUtils;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by Seweryn on 08.02.2017.
 *
 * Reguła pracownicza: Jeśli w poprzednim tygodniu pacował w
 * niedzielę to w aktualnym powinien mieć jeden dzisń wolny.
 *
 */
public class SundayWorkingRule extends DayValidator {

    private ICalendarService            calendarService;
    private IProfileService             profileService;
    private IAttendanceHistoryService   attendanceHistoryService;

    public SundayWorkingRule(ICalendarService calendarService, IProfileService profileService, IAttendanceHistoryService attendanceHistoryService) {
        super("Wymagany przynajmniej jeden dzień wolnego w tym tygodniu za ostatnią niedzielę");
        this.calendarService = calendarService;
        this.profileService = profileService;
        this.attendanceHistoryService = attendanceHistoryService;
    }

    public boolean isValid(AttendanceEmployee attendanceEmployee,AttendanceDay[] days, int dayNum) {
        UUID employeeId = attendanceEmployee.getEmployeeUniqueId();
        int workTimeType = profileService.getWorkTime(attendanceEmployee.getEmployeeUniqueId());
        if(workTimeType != IProfileService.WORK_TIME_FULL){
            return true;
        }
        AttendanceDay targetDay = days[dayNum];
        int dayOfWeek = calendarService.dayOfWeek(targetDay.getDate());
        int weekStart = dayNum - dayOfWeek;
        AttendanceDay lastSunday = null;
        if(weekStart-7 >= 0){
            lastSunday = days[weekStart-7];
        }else{
            Date dateOfLastSunday = DateUtils.dateMinusDays(targetDay.getDate(),Math.abs(weekStart-6));
            lastSunday = attendanceHistoryService.getAttendanceDay(employeeId,dateOfLastSunday);
        }
        if(lastSunday == null || !lastSunday.workScheduled()) {
            return true;
        }
        weekStart = Math.max(0,weekStart);
        int weekEnd = Math.min( days.length , dayNum + ( 7 - dayOfWeek ) );
        for (int i = weekStart ; i < weekEnd; i++) {
            AttendanceDay day = days[i];
            if(calendarService.isWorkingDay(day.getDate())
                    && !day.workScheduled()){
                return true;
            }
        }
        return false;
    }

}
