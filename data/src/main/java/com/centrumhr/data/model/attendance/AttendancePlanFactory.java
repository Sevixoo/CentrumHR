package com.centrumhr.data.model.attendance;

import com.centrumhr.data.model.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class AttendancePlanFactory {

    public AttendancePlan createPlan(String name , List<Employee> employees , Date month ){
        List<AttendanceEmployee> attendanceEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            attendanceEmployees.add( createAttendanceEmployee( employee , month ) );
        }
        AttendancePlan attendancePlan = new AttendancePlan();
        attendancePlan.setName(name);
        attendancePlan.setEmployees(attendanceEmployees);
        return attendancePlan;
    }

    private AttendanceEmployee createAttendanceEmployee( Employee employee, Date month ){
        AttendanceEmployee attendanceEmployee = new AttendanceEmployee( employee );
        List<AttendanceDay> attendanceDays = new ArrayList<>();
        int daysInMonth = getNumOfDaysInMonth(month);
        for ( int day = 1 ; day <= daysInMonth ; day++ ) {
            AttendanceDay attendanceDay = new AttendanceDay(day);
            attendanceDays.add(attendanceDay);
        }
        attendanceEmployee.setAttendanceDays( attendanceDays );
        return attendanceEmployee;
    }

    private int getNumOfDaysInMonth( Date month ){
        return 30;
    }

}
