package com.centrumhr.domain.schedule;

import com.centrumhr.domain.attendance.*;
import com.centrumhr.dto.common.Hour;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.attendance.AttendanceDayDTO;
import com.centrumhr.dto.attendance.AttendanceType;
import com.centrumhr.dto.employment.EmployeeDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 07.02.2017.
 */
public class ScheduleService {

    private AttendancePlanFactory       attendancePlanFactory;
    private ICalendarService            calendarService;

    public ScheduleService( AttendancePlanFactory attendancePlanFactory, ICalendarService calendarService ) {
        this.attendancePlanFactory = attendancePlanFactory;
        this.calendarService = calendarService;
    }

    public AttendancePlan createNewPlan(String name , List<EmployeeDTO> employees , Date month)throws DomainException{
        if(employees==null||employees.size()==0)throw new DomainException("Employees list cant be empty");
        if(month==null)throw new DomainException("Month cant be null");
        if(name==null)throw new DomainException("Name cant be null");

        AttendanceEmployee[] attendanceEmployees = new AttendanceEmployee[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            EmployeeDTO employeeDTO = employees.get(i);
            attendanceEmployees[i] = new AttendanceEmployee(
                    employeeDTO.getUniqueId(),
                    employeeDTO.getFirstName() + " " + employeeDTO.getSurname(),
                    employeeDTO.getCode()
            );
        }

        int numOfDaysInMonth = calendarService.getNumOfDaysInMonth(month);
        AttendanceDay[][] days = new AttendanceDay[employees.size()][numOfDaysInMonth];
        for(int i = 0 ; i < numOfDaysInMonth ; i++){
            for (int j = 0; j < attendanceEmployees.length; j++) {
                days[j][i] = new AttendanceDay( calendarService.dateOfDay( month , i ) , attendanceEmployees[j].getUniqueId() );
            }
        }
        return attendancePlanFactory.createNewPlan( name , month , attendanceEmployees , days );
    }

    public AttendanceDayDTO scheduleDay(AttendancePlan attendancePlan, UUID attendanceEmployeeId , int day , AttendanceType attendanceType , Hour hourFrom , Hour hourTo)throws DomainException{
        if(attendancePlan == null)throw new DomainException("Plan not selected");
        return attendancePlan.scheduleDay( attendanceEmployeeId , day , attendanceType , hourFrom , hourTo );
    }

    public AttendanceEmployeeSummary employeeSummary( AttendancePlan attendancePlan,UUID attendanceEmployeeId )throws DomainException{
        AttendanceEmployeeSummary attendanceEmployeeSummary = new AttendanceEmployeeSummary();
        attendanceEmployeeSummary.workingHours = attendancePlan.countWorkingDays(attendanceEmployeeId);
        attendanceEmployeeSummary.maxWorkingHours = attendancePlan.maxWorkingHours(attendanceEmployeeId);
        return attendanceEmployeeSummary;
    }

}
