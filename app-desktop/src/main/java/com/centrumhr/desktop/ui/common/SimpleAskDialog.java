package com.centrumhr.desktop.ui.common;

import com.centrumhr.desktop.core.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by Seweryn on 09.11.2016.
 */
public class SimpleAskDialog extends Controller {

    @FXML Button buttonCancel;
    @FXML Button buttonOK;
    @FXML Label messageLabel;

    private String message;

    public SimpleAskDialog( String message ) {
        super("layout/simple_ask_dialog.fxml");
        setResult(RESULT_CANCEL);
        setTitle("SimpleAskDialog");
        this.message =message;
    }

    @Override
    public void onStageCreated(Stage stage) {
        stage.resizableProperty().setValue(Boolean.FALSE);
    }

    @Override
    public void initialize() {
        messageLabel.setText(message);
        buttonCancel.setOnAction(event -> dismiss());
        buttonOK.setOnAction( event -> {
            setResult(RESULT_OK);
            dismiss();
        });
    }
}
