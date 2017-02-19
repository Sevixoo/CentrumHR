package com.centrumhr.desktop.ui.schedule.planner;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import com.centrumhr.desktop.ui.schedule.planner.component.AttendanceTypeComponent;
import com.centrumhr.dto.attendance.AttendanceType;
import com.centrumhr.dto.common.Hour;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Seweryn on 27.09.2016.
 */
public class AttendancePlanPickerController extends Controller implements AttendanceTypeComponent.Callback{

    public interface Callback{
        void onUpdateAttendanceDay( AttendanceType attendanceType, Hour hourFrom, Hour hourTo );
    }

    @FXML AttendanceTypeComponent work1Controller;
    @FXML AttendanceTypeComponent work2Controller;
    @FXML AttendanceTypeComponent vacationController;
    @FXML AttendanceTypeComponent medicalController;
    @FXML AttendanceTypeComponent freeDayController;


    private AttendanceDayVM attendanceDayVM;
    private Callback listener;

    public void setListener(Callback listener) {
        this.listener = listener;
    }

    public void setData( AttendanceDayVM attendanceDayVM) {
        this.attendanceDayVM = attendanceDayVM;
    }

    public AttendancePlanPickerController() {
        super("layout/schedule_attendance_plan_picker_scene.fxml");
        setTitle("Attendance plan picker");
    }

    @Override
    public void initialize() {
        work1Controller.setListener(this);
        work2Controller.setListener(this);
        vacationController.setListener(this);
        freeDayController.setListener(this);
        medicalController.setListener(this);
        work1Controller.setData("I zmiana",AttendanceType.NORMAL_WORK,new Hour("7:00"),new Hour("15:00"));
        work2Controller.setData("II zmiana",AttendanceType.NORMAL_WORK,new Hour("15:00"),new Hour("23:00"));
        freeDayController.setData(AttendanceType.FREE_DAY);
        medicalController.setData(AttendanceType.MEDICAL_LEAVE);
        vacationController.setData(AttendanceType.VACATION);
    }

    @Override
    public void onSelectAttendanceType(AttendanceType attendanceType, Hour hourFrom, Hour hourTo) {
        if(listener!=null){
            listener.onUpdateAttendanceDay(attendanceType, hourFrom, hourTo);
        }
        dismiss();
    }

    public AttendanceDayVM getData() {
        return attendanceDayVM;
    }
}
