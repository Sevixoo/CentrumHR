package com.centrumhr.desktop.ui;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.presenter.SplashPresenter;

import com.centrumhr.desktop.CentrumHRApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;

public class SplashController implements SplashPresenter.View {

    @Inject
    public SplashPresenter mPresenter;

    @FXML Pane container;

    public SplashController() {
        CentrumHRApplication.getInstance().getApplicationComponent().inject(this);
        mPresenter.setView(this);
        mPresenter.checkIfAccountIsLogged();
    }

    @Override
    public void showProgress(Message message) {
        System.out.println(message.name());
    }

    @Override
    public void hideProgress() {
        System.out.println("hideProgress");
    }

    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    @Override
    public void displayLoginScreen() {

    }

    @Override
    public void displayMainScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login_scene.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));

            stage.show();

            //hide this current window (if this is whant you want
            if(container!=null) {
                container.getScene().getWindow().hide();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
