package com.centrumhr.application.shedule.data;

import com.centrumhr.dto.common.Hour;
import com.centrumhr.dto.attendance.AttendanceType;

import java.util.UUID;

/**
 * Created by Seweryn on 11.02.2017.
 */
public class SetAttendanceDayTypeArgument {

    public UUID employeeUniqueId;
    public AttendanceType attendanceType;
    public int day;
    public Hour hourFrom;
    public Hour hourTo;

}
