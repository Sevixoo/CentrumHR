package com.centrumhr.domain.attendance;

import com.centrumhr.domain.attendance.validation.DayValidator;
import com.centrumhr.domain.attendance.validation.EmployeeValidator;
import com.centrumhr.dto.common.Hour;
import com.centrumhr.dto.attendance.AttendanceDayDTO;
import com.centrumhr.dto.attendance.AttendanceEmployeeDTO;
import com.centrumhr.dto.attendance.AttendancePlanDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class AttendancePlanFactory {

    private List<DayValidator>          dayValidators;
    private List<EmployeeValidator>     employeeValidators;

    public AttendancePlanFactory(List<DayValidator> dayValidators, List<EmployeeValidator> employeeValidators) {
        this.dayValidators = dayValidators;
        this.employeeValidators = employeeValidators;
    }

    public AttendancePlan createNewPlan(String name, Date month, AttendanceEmployee[] employees, AttendanceDay[][] days ){
        return new AttendancePlan( name, month, new Date(), employees,days, dayValidators, employeeValidators );
    }

    public AttendancePlan create(AttendancePlanDTO attendancePlanDTO){
        AttendanceEmployee[] attendanceEmployees = new AttendanceEmployee[attendancePlanDTO.getEmployees().length];
        for (int i = 0; i < attendancePlanDTO.getEmployees().length; i++) {
            AttendanceEmployeeDTO employeeDTO = attendancePlanDTO.getEmployees()[i];
            attendanceEmployees[i] = new AttendanceEmployee(
                    employeeDTO.getUniqueId(),
                    employeeDTO.getEmployeeUniqueId(),
                    employeeDTO.getName(),
                    employeeDTO.getCode()
            );
        }
        AttendanceDay[][] attendanceDays = new AttendanceDay[attendancePlanDTO.getDays().length][];
        for (int i = 0; i < attendancePlanDTO.getDays().length; i++) {
            attendanceDays[i] = new AttendanceDay[attendancePlanDTO.getDays()[i].length];
            for (int j = 0; j < attendancePlanDTO.getDays()[i].length; j++) {
                AttendanceDayDTO dayDTO =  attendancePlanDTO.getDays()[i][j];
                attendanceDays[i][j] = new AttendanceDay(
                    dayDTO.getUniqueId() ,
                    dayDTO.getEmployeeId(),
                    dayDTO.getType(),
                    Hour.create(dayDTO.getPlanStartHour()),
                    Hour.create(dayDTO.getPlanEndHour()),
                    Hour.create(dayDTO.getAttendStartHour()),
                    Hour.create(dayDTO.getAttendEndHour()),
                    dayDTO.getDate()
                );
            }
        }
        return new AttendancePlan(
                attendancePlanDTO.getUniqueId(),
                attendancePlanDTO.getName(),
                attendancePlanDTO.getStartDate(),
                attendancePlanDTO.getCreateDate(),
                attendancePlanDTO.getState(),
                attendanceEmployees,
                attendanceDays,
                dayValidators,
                employeeValidators
        );
    }

}
