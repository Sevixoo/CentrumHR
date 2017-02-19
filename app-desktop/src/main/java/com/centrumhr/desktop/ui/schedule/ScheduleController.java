package com.centrumhr.desktop.ui.schedule;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.creator.AddScheduleController;
import com.centrumhr.desktop.ui.schedule.data.ScheduleVM;
import com.centrumhr.desktop.ui.schedule.list.ScheduleListController;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.UUID;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class ScheduleController extends Controller {

    public interface Callback{
        void displayPlan(UUID planUniqueId);
    }

    @FXML Button        addScheduleButton;
    @FXML Button        editScheduleButton;
    @FXML Button        deleteScheduleButton;
    @FXML Pane          container;

    private Callback    callback;

    private ScheduleVM  selectedScheduleVM;

    public ScheduleController(Callback callback) {
        super("layout/schedule_controller.fxml");
        this.callback = callback;
    }

    @Override
    public void initialize() {
        displayScheduleList();
        editScheduleButton.setDisable(true);
        deleteScheduleButton.setDisable(true);
        addScheduleButton.setOnAction(event -> displayAddPlanDialog() );
        editScheduleButton.setOnAction( event -> displayEditPlan() );
    }

    public void displayScheduleList(){
        ScheduleListController scheduleListController = new ScheduleListController();
        scheduleListController.setListener( this::onSelectSchedule );
        displayComponent( container , scheduleListController );
    }

    private void onSelectSchedule(ScheduleVM scheduleVM){
        selectedScheduleVM = scheduleVM;
        if(scheduleVM == null) {
            editScheduleButton.setDisable(true);
            deleteScheduleButton.setDisable(true);
        }else{
            editScheduleButton.setDisable(false);
            deleteScheduleButton.setDisable(false);
        }
    }

    private void displayAddPlanDialog(){
        AddScheduleController dialog = new AddScheduleController();
        dialog.startForResult( this );
        if(dialog.getResult() == RESULT_OK){
            callback.displayPlan(dialog.getData().getUniqueId());
        }
    }

    public void displayEditPlan(){
        if(selectedScheduleVM!=null){
            callback.displayPlan(selectedScheduleVM.getUuid());
        }
    }

}
