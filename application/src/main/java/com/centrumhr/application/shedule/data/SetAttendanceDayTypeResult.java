package com.centrumhr.application.shedule.data;

import com.centrumhr.domain.schedule.AttendanceEmployeeSummary;
import com.centrumhr.dto.attendance.AttendanceDayDTO;
import com.centrumhr.dto.attendance.AttendanceEmployeeDTO;
import com.centrumhr.dto.attendance.AttendanceType;
import com.centrumhr.dto.common.Hour;

import java.util.UUID;

/**
 * Created by Seweryn on 11.02.2017.
 */
public class SetAttendanceDayTypeResult {


    public AttendanceDayDTO changedDay;
    public UUID employeeId;
    public AttendanceEmployeeSummary employeeSummary;

}
