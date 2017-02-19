package com.centrumhr.desktop.ui.common;

import com.centrumhr.desktop.core.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Seweryn on 09.11.2016.
 */
public class ErrorDialog extends Controller {

    @FXML Button buttonOK;
    @FXML Label messageLabel;
    @FXML TextArea descriptionLabel;

    private String message;
    private String description;

    public ErrorDialog( String title, String message , String description ) {
        super("layout/error_dialog.fxml");
        setResult(RESULT_CANCEL);
        setTitle(title);
        this.message = message;
        this.description =  description;
        if(message == null || message.equals("")){
            this.message = "Error";
        }
    }

    @Override
    public void onStageCreated(Stage stage) {
        stage.resizableProperty().setValue(Boolean.FALSE);
    }

    @Override
    public void initialize() {
        messageLabel.setText(message);
        descriptionLabel.setText(description);
        buttonOK.setOnAction( event -> {
            setResult(RESULT_OK);
            dismiss();
        });
    }
}
