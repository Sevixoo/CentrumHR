package com.centrumhr.desktop.ui.settings;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController;
import com.centrumhr.desktop.ui.settings.department.DepartmentSettingsController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class SettingsController extends Controller {

    @FXML Button departmentButton;
    @FXML Pane container;

    public SettingsController() {
        super("layout/settings_controller.fxml");
    }

    @Override
    public void initialize() {
        departmentButton.setOnAction(event -> displayComponent( container , new DepartmentSettingsController()));
    }

}
