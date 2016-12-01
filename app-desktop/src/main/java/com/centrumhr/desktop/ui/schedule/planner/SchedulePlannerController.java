package com.centrumhr.desktop.ui.schedule.planner;

import com.centrumhr.application.presenter.shedule.AttendancePlanPresenter;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.model.attendance.*;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import com.centrumhr.desktop.ui.schedule.data.AttendanceEmployeeVM;
import com.centrumhr.desktop.ui.schedule.planner.adapter.PlannerTableAdapter;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;

import javax.inject.Inject;
import java.util.*;
import java.util.List;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class SchedulePlannerController extends Controller implements AttendancePlanPresenter.View {

    @FXML PlannerMenuComponent              plannerMenuController;
    @FXML TableView<AttendanceEmployeeVM>   scheduleTable;

    private PlannerTableAdapter             mPlannerTableAdapter;
    private AttendancePlan                  mAttendancePlan;
    private AttendancePlanPickerController  mActivePopup;

    @Inject AttendancePlanPresenter         mPresenter;

    private UUID                            mAttendancePlanUniqueId;

    public SchedulePlannerController( UUID attendancePlanUniqueId ){
        super("layout/schedule_planner_controller.fxml");
        mAttendancePlanUniqueId = attendancePlanUniqueId;
    }

    @Override
    public void initialize() {
        initializeInjection();
        mPresenter.setView(this);
        plannerMenuController.setListener(new PlannerMenuComponent.Callback() {
            @Override
            public void onClickSave() {
                savePlan();
            }

            @Override
            public void onClickBack() {

            }
        });
        mPlannerTableAdapter = new PlannerTableAdapter(scheduleTable);
        mPlannerTableAdapter.setListener(new PlannerTableAdapter.Callback() {
            @Override
            public void onAttendanceDayClick(AttendanceDayVM dayVM, TableCell tableCell) {
                displayPickerDialog( dayVM , tableCell );
            }

            @Override
            public void onEmployeeClick(AttendanceEmployeeVM employeeVM) {

            }
        });
        scheduleTable.setOnKeyPressed(event -> onPressKeyTableView(event.getCode()));
        mPresenter.loadAttendancePlan( mAttendancePlanUniqueId );
    }

    private void initializeInjection(){
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
    }

    private void displayPickerDialog(AttendanceDayVM attendanceDayVM, TableCell tableCell){
        if(mActivePopup!=null){
            mActivePopup.dismiss();
            if(mActivePopup.getData().equals(attendanceDayVM)){
                mActivePopup = null;
                return;
            }
        }
        mActivePopup = new AttendancePlanPickerController();
        mActivePopup.setData( mAttendancePlan , attendanceDayVM );
        mActivePopup.setListener(new AttendancePlanPickerController.Callback() {
            @Override
            public void onUpdateAttendanceDay(AttendanceDay attendanceDay) {
                AttendanceDayVM attendanceDayVM = new AttendanceDayVM(attendanceDay);
                mPlannerTableAdapter.refresh( attendanceDayVM );

            }
        });
        Bounds boundsInScreen = tableCell.localToScreen(tableCell.getBoundsInLocal());
        mActivePopup.displayPopOver( scheduleTable.getScene().getWindow() ,
                boundsInScreen.getMinX() + tableCell.getWidth() ,
                boundsInScreen.getMinY()
        );
    }

    private void onPressKeyTableView(KeyCode code){
        if(code == KeyCode.ENTER){
            Object item =  mPlannerTableAdapter.getSelectedItem(  );
            if(item instanceof AttendanceDayVM){
                AttendanceDayVM attendanceDayVM = (AttendanceDayVM)item;
                displayPickerDialog( attendanceDayVM , mPlannerTableAdapter.getSelectedTableCell() );
            }
        }
    }

    private void savePlan(){
        mAttendancePlan.setName(plannerMenuController.getName());
        mPresenter.savePlan( mAttendancePlan );
    }

    @Override
    public void displayAttendancePlan(AttendancePlan attendancePlan) {
        mAttendancePlan = attendancePlan;
        plannerMenuController.displayName(mAttendancePlan.getName());
        mPlannerTableAdapter.setData( attendancePlan.getEmployees() );
    }

    @Override
    public void displayError(String message) {

    }

}
