package com.centrumhr.desktop.ui.schedule.data;

import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendanceEmployee;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class AttendanceEmployeeVM {

    public String name;
    public String hours;
    public List<ObservableValue<AttendanceDayVM>> data;

    public AttendanceEmployeeVM(AttendanceEmployee attendanceEmployee) {
        name = attendanceEmployee.getName();
        hours = "0/0/0";
        data = new ArrayList<>();
        for (AttendanceDay attendanceDay : attendanceEmployee.getAttendanceDays()) {
            AttendanceDayVM attendanceDayVM = new AttendanceDayVM(attendanceDay);
            data.add( new ReadOnlyObjectWrapper<>(attendanceDayVM) );
        }
    }
}
