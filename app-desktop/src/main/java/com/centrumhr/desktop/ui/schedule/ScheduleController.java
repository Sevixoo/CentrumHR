package com.centrumhr.desktop.ui.schedule;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class ScheduleController extends Controller {

    @FXML Button addScheduleButton;
    @FXML Pane container;

    public ScheduleController() {
        super("layout/schedule_controller.fxml");
    }

    @Override
    public void initialize() {
        addScheduleButton.setOnAction(event -> displayComponent( container , new SchedulePlannerController()));
    }
}
