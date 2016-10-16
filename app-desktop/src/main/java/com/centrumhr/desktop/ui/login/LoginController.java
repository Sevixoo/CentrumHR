package com.centrumhr.desktop.ui.login;

import com.centrumhr.desktop.ui.main.MainController;
import com.centrumhr.desktop.core.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class LoginController extends Controller {

    @FXML Pane loginForm;
    @FXML Pane createAccountForm;
    @FXML Hyperlink buttonRegister;
    @FXML Hyperlink buttonLogin;

    @FXML Button buttonCreateAccount;

    public LoginController() {
        super("layout/login_scene.fxml");
        setTitle("Logowanie do systemu");
    }

    @Override
    public void onStageCreated( Stage stage) {
        stage.setResizable(false);
    }

    @FXML public void initialize(){
        buttonRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayRegisterScreen();
            }
        });
        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayLoginForm();
            }
        });
        buttonCreateAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayMainScreen();
            }
        });
    }

    private void displayMainScreen(){
        new MainController().start();
        finish();
    }

    private void displayLoginForm(){
        loginForm.setVisible(true);
        createAccountForm.setVisible(false);
    }

    private void displayRegisterScreen(){
        loginForm.setVisible(false);
        createAccountForm.setVisible(true);
    }

    @FXML void onClickSubmit( ActionEvent event ){
        ((Button) event.getSource()).setDisable(true);
    }


}
