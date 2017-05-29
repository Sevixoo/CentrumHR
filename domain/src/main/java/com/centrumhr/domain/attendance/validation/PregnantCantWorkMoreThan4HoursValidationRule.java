package com.centrumhr.domain.attendance.validation;

import com.centrumhr.domain.attendance.AttendanceDay;
import com.centrumhr.domain.attendance.AttendanceEmployee;
import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.attendance.IProfileService;

/**
 * Created by Seweryn on 08.02.2017.
 */
public class PregnantCantWorkMoreThan4HoursValidationRule extends DayValidator {

    private IProfileService profileService;

    public PregnantCantWorkMoreThan4HoursValidationRule(IProfileService profileService) {
        super("PREGNANT_CANT_WORK_MORE_THAN_4_HOURS_ERROR");
        this.profileService = profileService;
    }

    public boolean isValid(AttendanceEmployee attendanceEmployee, AttendanceDay[] days, int dayNum ) {
        if(!profileService.isPregnant(attendanceEmployee.getEmployeeUniqueId())){
            return false;
        }
        return days[dayNum].workScheduled() && days[dayNum].workDuration() > 4*60;
    }
}
