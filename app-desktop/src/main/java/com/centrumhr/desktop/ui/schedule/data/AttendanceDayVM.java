package com.centrumhr.desktop.ui.schedule.data;

import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendanceType;

import java.util.UUID;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class AttendanceDayVM {

    public String label;
    public String label2;
    public String color;

    public UUID employeeUniqueId;
    public UUID attendanceDayUniqueId;

    public AttendanceDayVM(AttendanceDay attendanceDay) {
        employeeUniqueId = attendanceDay.getEmployee().getUniqueId();
        attendanceDayUniqueId = attendanceDay.getUniqueId();
        if(attendanceDay.getType() == AttendanceType.NORMAL) {
            label = attendanceDay.getPlanStartHour().format() + "\n" + attendanceDay.getPlanEndHour().format();
            label2 = "8";
            color = "rgba(76, 175, 80, 0.64)";
        }else if(attendanceDay.getType() == AttendanceType.L4){
            label = "   ";
            color = "rgba(244, 67, 54, 0.64)";
            label2 = "L4";
        }else{
            color = "rgba(255, 255, 255, 0.64)";
        }
    }
}
