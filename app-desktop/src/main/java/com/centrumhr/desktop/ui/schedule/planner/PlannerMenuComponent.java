package com.centrumhr.desktop.ui.schedule.planner;

import com.centrumhr.data.model.attendance.AttendancePlan;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class PlannerMenuComponent {

    public interface Callback{
        void onClickSave();
        void onClickBack();
    }

    @FXML Button buttonSave;
    @FXML Button buttonBack;
    @FXML TextField textFieldName;

    private Callback mCallback;

    @FXML void initialize(){
        buttonSave.setOnAction(event -> mCallback.onClickSave());
        buttonBack.setOnAction(event -> mCallback.onClickBack());
    }

    public void setListener( Callback callback ){
        mCallback = callback;
    }

    public void displayName(String name){
        textFieldName.setText(name);
    }

    public String getName(){
        return textFieldName.getText();
    }

}
