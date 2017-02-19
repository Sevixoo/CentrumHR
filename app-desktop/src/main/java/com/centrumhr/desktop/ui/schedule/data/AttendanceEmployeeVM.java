package com.centrumhr.desktop.ui.schedule.data;

import com.centrumhr.data.model.attendance.AttendanceDayEntity;
import com.centrumhr.data.model.attendance.AttendanceEmployeeEntity;
import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.schedule.AttendanceEmployeeSummary;
import com.centrumhr.dto.attendance.AttendanceDayDTO;
import com.centrumhr.dto.attendance.AttendanceEmployeeDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class AttendanceEmployeeVM {

    public String name;
    public String hours;
    public List<ObservableValue<AttendanceDayVM>> data;
    public UUID uniqueId;

    public AttendanceEmployeeVM(AttendanceEmployeeDTO attendanceEmployeeDTO, AttendanceDayDTO[] dayDTOs,ICalendarService calendarService ) {
        name = attendanceEmployeeDTO.getName();
        uniqueId = attendanceEmployeeDTO.getUniqueId();
        data = new ArrayList<>();
        for (int i = 0; i < dayDTOs.length; i++) {
            AttendanceDayVM attendanceDayVM = new AttendanceDayVM(dayDTOs[i],i,calendarService);
            data.add( new ReadOnlyObjectWrapper<>(attendanceDayVM) );
        }
    }

    public void setSummary(AttendanceEmployeeSummary summary){
        if(summary!=null) {
            hours = summary.workingHours + "/" + summary.maxWorkingHours;
        }else{
            hours = "0/0";
        }
    }

}
