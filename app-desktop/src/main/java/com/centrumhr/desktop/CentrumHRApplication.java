package com.centrumhr.desktop;

import com.centrumhr.desktop.di.AppComponent;
import com.centrumhr.desktop.di.ApplicationModule;
import com.centrumhr.desktop.di.DaggerAppComponent;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class CentrumHRApplication extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static CentrumHRApplication mInstance;

    public static CentrumHRApplication getInstance() {
        return mInstance;
    }

    private AppComponent mApplicationComponent;

    @Override
    public void start(Stage stage) throws Exception {
        mInstance = this;

        mApplicationComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        displayLauncherScreen(stage);
    }

    private void displayLauncherScreen(Stage stage){
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = loadView("splash_scene.fxml");

        Scene scene = new Scene(root, 300, 275);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    public Parent loadView( String resource ){
        URL url = ClassLoader.getSystemClassLoader().getResource(resource);
        try {
            return FXMLLoader.load(url);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException( "view:" + resource + " not found url:" + url.getPath()  );
        }
    }

    public AppComponent getApplicationComponent(){
        return mApplicationComponent;
    }


}
