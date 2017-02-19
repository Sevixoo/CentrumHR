package com.centrumhr.desktop.ui.schedule.planner.component;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class PlannerMenuComponent {

    public interface Callback{
        void onClickBack();
        void onClickSave();
        void onTitleChanged(String title);
    }

    @FXML Button buttonBack;
    @FXML Button  buttonSave;
    @FXML TextField textFieldName;

    private Callback mCallback;

    @FXML void initialize(){
        buttonBack.setOnAction(event -> mCallback.onClickBack());
        buttonSave.setOnAction(event -> mCallback.onClickSave());
        textFieldName.textProperty().addListener( (observable, oldValue, newValue) -> {
            if(oldValue!=null && !oldValue.equals(newValue)){
                mCallback.onTitleChanged( newValue );
            }
        });
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
