package com.centrumhr.desktop.ui.common;

import com.centrumhr.desktop.core.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Created by Seweryn on 09.11.2016.
 */
public class LoadingDialog extends Controller {

    public LoadingDialog(String title) {
        super("layout/loading_dialog.fxml");
        setResult(RESULT_CANCEL);
        setTitle(title);
    }

    @Override
    public void onStageCreated(Stage stage) {
        stage.resizableProperty().setValue(Boolean.FALSE);
    }

    @Override
    public void initialize() { }
}
