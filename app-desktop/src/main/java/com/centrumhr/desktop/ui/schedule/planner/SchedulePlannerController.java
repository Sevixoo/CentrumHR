package com.centrumhr.desktop.ui.schedule.planner;

import com.centrumhr.desktop.ui.schedule.data.AttendancePlanVM;
import com.centrumhr.desktop.ui.schedule.planner.presenter.AttendancePlanPresenter;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import com.centrumhr.desktop.ui.schedule.data.AttendanceEmployeeVM;
import com.centrumhr.desktop.ui.schedule.planner.adapter.PlannerTableAdapter;
import com.centrumhr.domain.schedule.AttendanceEmployeeSummary;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import com.centrumhr.desktop.ui.schedule.planner.component.PlannerMenuComponent;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class SchedulePlannerController extends Controller implements AttendancePlanPresenter.View {

    public interface Callback{
        void displayScheduleList();
    }

    @FXML PlannerMenuComponent              plannerMenuController;
    @FXML TableView<AttendanceEmployeeVM>   scheduleTable;

    private PlannerTableAdapter             plannerTableAdapter;
    private AttendancePlanVM                viewModel;
    private AttendancePlanPickerController  activePopup;

    @Inject AttendancePlanPresenter         presenter;

    private UUID                            attendancePlanUniqueId;
    private Callback                        callback;

    public SchedulePlannerController( Callback callback , UUID attendancePlanUniqueId ){
        super("layout/schedule_planner_controller.fxml");
        this.attendancePlanUniqueId = attendancePlanUniqueId;
        this.callback = callback;
    }

    @Override
    public void initialize() {
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
        presenter.setView(this);
        plannerMenuController.setListener(new PlannerMenuComponent.Callback() {
            @Override
            public void onClickBack() {
                callback.displayScheduleList();
            }

            @Override
            public void onClickSave() {
                presenter.saveAttendancePlan();
            }

            @Override
            public void onTitleChanged(String name) {
                presenter.changeAttendancePlanName(name);
            }
        });
        plannerTableAdapter = new PlannerTableAdapter(scheduleTable);
        plannerTableAdapter.setListener(new PlannerTableAdapter.Callback() {
            @Override
            public void onAttendanceDayClick(AttendanceDayVM dayVM, TableCell tableCell) {
                if(!dayVM.isFreeDay){
                    displayPickerDialog( dayVM , tableCell );
                }
            }

            @Override
            public void onEmployeeClick(AttendanceEmployeeVM employeeVM) {

            }
        });
        scheduleTable.setOnKeyPressed(event -> onPressKeyTableView(event.getCode()));
        presenter.loadAttendancePlan( attendancePlanUniqueId );
    }

    private void displayPickerDialog(AttendanceDayVM attendanceDayVM, TableCell tableCell){
        if(activePopup!=null){
            activePopup.dismiss();
            if(activePopup.getData().equals(attendanceDayVM)){
                activePopup = null;
                return;
            }
        }
        activePopup = new AttendancePlanPickerController();
        activePopup.setData( attendanceDayVM );
        activePopup.setListener((attendanceType, hourFrom, hourTo) -> {
            presenter.setAttendanceDayType( attendanceDayVM , attendanceType , hourFrom , hourTo);
        });
        Bounds boundsInScreen = tableCell.localToScreen(tableCell.getBoundsInLocal());
        activePopup.displayPopOver( scheduleTable.getScene().getWindow() ,
                boundsInScreen.getMinX() + tableCell.getWidth() ,
                boundsInScreen.getMinY()
        );
    }

    @Override
    public void onUpdateAttendanceDay(AttendanceDayVM attendanceDay) {
        plannerTableAdapter.refresh( attendanceDay );
    }

    @Override
    public void onUpdateEmployeeSummary(UUID employeeId, AttendanceEmployeeSummary employeeSummary) {
        plannerTableAdapter.refresh( employeeId , employeeSummary );
    }

    private void onPressKeyTableView(KeyCode code){
        if(code == KeyCode.ENTER){
            Object item =  plannerTableAdapter.getSelectedItem(  );
            if(item instanceof AttendanceDayVM){
                AttendanceDayVM attendanceDayVM = (AttendanceDayVM)item;
                displayPickerDialog( attendanceDayVM , plannerTableAdapter.getSelectedTableCell() );
            }
        }
    }

    @Override
    public void displayAttendancePlan(AttendancePlanVM attendancePlanVM) {
        viewModel = attendancePlanVM;
        plannerMenuController.displayName(viewModel.getName());
        plannerTableAdapter.setData( viewModel.getEmployees() );
    }

}
