package com.centrumhr.desktop.core;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

/**
 * Created by Seweryn on 23.09.2016.
 */
public class SceneManager {


    private static SceneManager instance;

    public static SceneManager getInstance(){
        return instance;
    }

    public SceneManager( ){
        instance = this;
    }

    public void displayScene( Controller controller , Stage stage ) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(controller);
        controller.setStage( stage );
        show(viewRootNodeHierarchy,stage, controller.getTitle(),false);
    }

    public void displayScene( Controller controller ) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(controller);
        Stage stage = new Stage();
        controller.setStage( stage );
        show(viewRootNodeHierarchy,stage, controller.getTitle(),false);
    }

    public void displayForResult( Controller parent, Controller controller ) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(controller);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parent.getStage());
        controller.setStage( stage );
        show(viewRootNodeHierarchy, stage , controller.getTitle(),true);
    }

    public Parent load( Controller parent , Controller controller ) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(controller);
        Stage stage = parent.getStage();
        controller.setStage( stage );
        return viewRootNodeHierarchy;
    }

    private void show(final Parent rootNode,Stage stage , String title, boolean blocking) {
         Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(rootNode);
        }
        scene.setRoot(rootNode);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();

        URL image = ClassLoader.getSystemClassLoader().getResource("icon/ic_launcher.png");
        if(image!=null) {
            stage.getIcons().add(new Image(image.toString()));
        }

        try {
            if(blocking){
                stage.showAndWait();
            }else {
                stage.show();
            }
        } catch (Exception exception) {
            logAndExit ("Unable to show scene for title" + title,  exception);
        }
    }


    /**
     * Loads the object hierarchy from a FXML document and returns to root node
     * of that hierarchy.
     *
     * @return Parent root node of the FXML document hierarchy
     */
    private Parent loadViewNodeHierarchy( Controller controller ) {
        Parent rootNode = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController( controller );
            loader.setLocation(ClassLoader.getSystemClassLoader().getResource( controller.getResource() ));
            rootNode = loader.load();
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            logAndExit("Unable to load FXML view" + controller.getResource(), exception);
        }
        return rootNode;
    }

    private void logAndExit(String errorMsg, Exception exception) {
        System.err.println(errorMsg + ":" + exception.getMessage() );
        exception.printStackTrace();
        Platform.exit();
    }

}
