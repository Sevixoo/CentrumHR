package com.centrumhr.domain.attendance;

import com.centrumhr.dto.attendance.AttendanceType;
import com.centrumhr.dto.common.Hour;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Seweryn on 28.05.2017.
 */
public class MockHistoryService implements IAttendanceHistoryService {

    public static UUID EMPLOYEE_THAT_WORKED_LAST_SUNDAY = UUID.randomUUID();
    public static UUID EMPLOYEE_THAT_NOT_WORKED_LAST_SUNDAY = UUID.randomUUID();

    @Override
    public AttendanceDay getAttendanceDay(UUID employeeUniqueId, Date date) {
        if(EMPLOYEE_THAT_WORKED_LAST_SUNDAY.equals(employeeUniqueId)){
            AttendanceDay attendanceDay = new AttendanceDay(date,employeeUniqueId);
            attendanceDay.schedule(AttendanceType.NORMAL_WORK,new Hour("08:00"),new Hour("16:00"));
            return attendanceDay;
        }
        return null;
    }

}
