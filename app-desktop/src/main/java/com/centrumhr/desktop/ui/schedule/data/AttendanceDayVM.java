package com.centrumhr.desktop.ui.schedule.data;

import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.dto.attendance.AttendanceDayDTO;
import com.centrumhr.dto.attendance.AttendanceEmployeeDTO;
import com.centrumhr.dto.attendance.AttendanceType;
import com.centrumhr.dto.common.Hour;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class AttendanceDayVM {

    public String label;
    public String label2;
    public String color;
    public String name;
    public boolean isFreeDay;

    public UUID employeeUniqueId;
    public UUID attendanceDayUniqueId;
    public int day;

    public AttendanceDayVM(AttendanceDayDTO attendanceDayDTO, int day,ICalendarService calendarService) {
        this.employeeUniqueId = attendanceDayDTO.getEmployeeId();
        this.day = day;
        this.attendanceDayUniqueId = attendanceDayDTO.getUniqueId();
        this.name = new SimpleDateFormat("dd-MM, EEE", Locale.getDefault()).format(attendanceDayDTO.getDate());
        this.isFreeDay = !calendarService.isWorkingDay( attendanceDayDTO.getDate() );
        if(this.isFreeDay){
            this.label2 = "  N";
            this.color = "rgba(255, 255, 255, 0.64)";
        }else {
            AttendanceType attendanceType = attendanceDayDTO.getType();
            if (attendanceType.requiresTime()) {
                int duration = Hour.durationHours(attendanceDayDTO.getPlanStartHour(), attendanceDayDTO.getPlanEndHour());
                this.label = attendanceDayDTO.getPlanStartHour().format() + "\n" + attendanceDayDTO.getPlanEndHour().format();
                this.label2 = String.valueOf(duration);
            } else {
                this.label = "   ";
                this.label2 = attendanceType.getLabel();
            }
            this.color = attendanceType.getColor();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceDayVM)) return false;
        AttendanceDayVM that = (AttendanceDayVM) o;
        return attendanceDayUniqueId.equals(that.attendanceDayUniqueId);

    }

    @Override
    public int hashCode() {
        return attendanceDayUniqueId.hashCode();
    }
}
