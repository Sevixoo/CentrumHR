package com.centrumhr.desktop.ui.start;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.presenter.SplashPresenter;

import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.inject.Inject;

public class SplashController extends Controller implements SplashPresenter.View {

    @Inject
    public SplashPresenter mPresenter;

    public SplashController() {
        super("layout/splash_scene.fxml");
    }

    @Override
    public void onStageCreated( Stage stage ) {
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @FXML
    public void initialize() {
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
        new com.centrumhr.desktop.ui.login.LoginController().start();
        finish();
    }

    @Override
    public void displayMainScreen() {
        new com.centrumhr.desktop.ui.main.MainController().start();
        finish();
    }

}
