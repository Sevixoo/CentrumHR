package com.centrumhr.desktop;

import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.desktop.di.*;
import com.centrumhr.desktop.ui.start.SplashController;
import com.centrumhr.desktop.core.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class CentrumHRApplication extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static CentrumHRApplication mInstance;

    public static CentrumHRApplication getInstance() {
        return mInstance;
    }

    private AppComponent mApplicationComponent;
    private LoggedAccountComponent mLoggedAccountComponent;
    private SceneManager mSceneManager;

    @Override
    public void start(Stage stage) throws Exception {
        mInstance = this;

        mSceneManager = new SceneManager(  );

        mApplicationComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        displayLauncherScreen(stage);
    }

    private void displayLauncherScreen(Stage stage){
        mSceneManager.displayScene( new SplashController() , stage );
    }

    public AppComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    public LoggedAccountComponent getLoggedAccountComponent(){
        return mLoggedAccountComponent;
    }

    public void setLoggedAccount(AccountData account){
        if (account == null) {
            mLoggedAccountComponent = null;
        } else {
            mLoggedAccountComponent = mApplicationComponent
                    .getLoggedAccountComponent(new DataBaseModule(
                            mApplicationComponent.getDataBaseService()
                    ), new AccountModule());
        }
    }

}
