package com.centrumhr.desktop.ui.schedule.planner;

import com.centrumhr.application.presenter.shedule.AttendancePlanPickerPresenter;
import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.attendance.AttendanceType;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.inject.Inject;

/**
 * Created by Seweryn on 27.09.2016.
 */
public class AttendancePlanPickerController extends Controller implements AttendancePlanPickerPresenter.View {

    public interface Callback{
        void onUpdateAttendanceDay( AttendanceDay attendanceDayVM );
    }

    private AttendanceDayVM data;
    @Inject AttendancePlanPickerPresenter mPresenter;

    @FXML Button buttonAttentTypeNormal;
    @FXML Button buttonAttentTypeL4;

    AttendancePlan attendancePlan;
    Callback listener;

    public void setListener(Callback listener) {
        this.listener = listener;
    }

    public void setData(AttendancePlan attendancePlan, AttendanceDayVM data) {
        this.data = data;
        this.attendancePlan = attendancePlan;
    }

    public AttendancePlanPickerController() {
        super("layout/schedule_attendance_plan_picker_scene.fxml");
        setTitle("Attendance plan picker");
    }

    @Override
    public void initialize() {
        initializeInjection();
        mPresenter.setView(this);
        buttonAttentTypeNormal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setAttendanceType(AttendanceType.NORMAL);
            }
        });
        buttonAttentTypeL4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setAttendanceType(AttendanceType.L4);
            }
        });
    }

    private void setAttendanceType(AttendanceType attendanceType){
        mPresenter.setAttendanceDayType( attendancePlan , data.employeeUniqueId , data.attendanceDayUniqueId, attendanceType );
    }

    private void initializeInjection(){
        mPresenter = CentrumHRApplication.getInstance().getLoggedAccountComponent().getAttendancePlanPickerPresenter();
    }

    public AttendanceDayVM getData() {
        return data;
    }

    @Override
    public void onUpdateAttendanceDay(AttendanceDay attendanceDay) {
        if(listener!=null){
            listener.onUpdateAttendanceDay(attendanceDay);
        }
        dismiss();
    }

    @Override
    public void displayError(String message) {
        System.out.println(message);
    }
}
