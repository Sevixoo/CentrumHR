package com.centrumhr.desktop.ui.main;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.dashboard.DashboardController;
import com.centrumhr.desktop.ui.employee.EmployeeController;
import com.centrumhr.desktop.ui.report.ReportController;
import com.centrumhr.desktop.ui.schedule.ScheduleController;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController;
import com.centrumhr.desktop.ui.settings.SettingsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.UUID;

/**
 * Created by Seweryn on 24.09.2016.
 */
public class MenuComponent implements ScheduleController.Callback,SchedulePlannerController.Callback {

    public interface Callback{
        void displayComponent( Controller controller );
    }

    private Callback listener;

    public void setListener(Callback listener) {
        this.listener = listener;
    }

    @FXML Button menuButtonDashboard;
    @FXML Button menuButtonReport;
    @FXML Button menuButtonEmployee;
    @FXML Button menuButtonSchedule;
    @FXML Button menuButtonSettings;

    @FXML void initialize(){
        menuButtonEmployee.setOnAction( event -> listener.displayComponent( new EmployeeController()));
        menuButtonSchedule.setOnAction(event -> displayScheduleList());
        menuButtonReport.setOnAction(event -> listener.displayComponent(new ReportController()));
        menuButtonSettings.setOnAction(event -> listener.displayComponent(new SettingsController()));
        menuButtonDashboard.setOnAction(event -> listener.displayComponent(new DashboardController()));
        menuButtonSettings.setVisible(false);
    }

    @Override
    public void displayScheduleList() {
        listener.displayComponent( new ScheduleController(this) );
    }

    @Override
    public void displayPlan(UUID planUniqueId) {
        listener.displayComponent( new SchedulePlannerController( this, planUniqueId ));
    }
}
