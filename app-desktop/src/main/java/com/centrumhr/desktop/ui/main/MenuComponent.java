package com.centrumhr.desktop.ui.main;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.employee.EmployeeController;
import com.centrumhr.desktop.ui.report.ReportController;
import com.centrumhr.desktop.ui.schedule.ScheduleController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Seweryn on 24.09.2016.
 */
public class MenuComponent {

    public interface Callback{
        void displayComponent( Controller controller );
    }

    private Callback listener;

    public void setListener(Callback listener) {
        this.listener = listener;
    }

    @FXML Button menuButtonReport;
    @FXML Button menuButtonEmployee;
    @FXML Button menuButtonSchedule;

    @FXML void initialize(){
        menuButtonEmployee.setOnAction( event -> listener.displayComponent( new EmployeeController()));
        menuButtonSchedule.setOnAction(event -> listener.displayComponent( new ScheduleController() ));
        menuButtonReport.setOnAction(event -> listener.displayComponent(new ReportController()));
    }


}
