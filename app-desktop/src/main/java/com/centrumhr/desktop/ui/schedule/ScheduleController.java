package com.centrumhr.desktop.ui.schedule;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.creator.AddScheduleController;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.UUID;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class ScheduleController extends Controller {

    @FXML Button        addScheduleButton;
    @FXML Pane          container;

    public ScheduleController() {
        super("layout/schedule_controller.fxml");
    }

    @Override
    public void initialize() {
        addScheduleButton.setOnAction(event -> displayAddPlanDialog() );
    }

    private void displayAddPlanDialog(){
        AddScheduleController dialog = new AddScheduleController();
        dialog.startForResult( this );
        if(dialog.getResult() == RESULT_OK){
            displayPlan( dialog.getData().getUniqueId() );
        }
    }

    private void displayPlan(UUID planUniqueId){
        displayComponent( container , new SchedulePlannerController( planUniqueId ));
    }

}
