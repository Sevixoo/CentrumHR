package com.centrumhr.desktop.ui.common;

import com.centrumhr.desktop.core.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by Seweryn on 09.11.2016.
 */
public class SimpleAskDialog extends Controller {

    @FXML Button buttonCancel;
    @FXML Button buttonOK;
    @FXML Label messageLabel;

    String message;

    public SimpleAskDialog( String msg ) {
        super("layout/simple_ask_dialog.fxml");
        setResult(RESULT_CANCEL);
        message =msg;
    }

    @Override
    public void initialize() {
        messageLabel.setText(message);
        buttonCancel.setOnAction(event -> { dismiss(); });
        buttonOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setResult(RESULT_OK);
                dismiss();
            }
        });
    }
}
