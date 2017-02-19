package com.centrumhr.desktop.ui.main;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.dashboard.DashboardController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Created by Seweryn on 22.09.2016.
 */
public class MainController extends Controller implements MenuComponent.Callback{

    @FXML MenuComponent menuController;
    @FXML Pane container;

    public MainController( ) {
        super("layout/main_scene.fxml");
    }

    public void initialize(){
        menuController.setListener( this );
        displayComponent(new DashboardController());
    }

    @Override
    public void displayComponent( Controller controller ){
        displayComponent( container , controller );
    }

}
