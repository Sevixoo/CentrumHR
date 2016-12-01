package com.centrumhr.desktop.ui.schedule.planner.adapter;

import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import com.centrumhr.desktop.ui.schedule.data.AttendanceEmployeeVM;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import javax.imageio.ImageIO;

/**
 * Created by Seweryn on 16.10.2016.
 */
public class EmployeeCell extends TableCell<AttendanceEmployeeVM,AttendanceEmployeeVM>{

    public static Callback<TableColumn.CellDataFeatures<AttendanceEmployeeVM,AttendanceEmployeeVM>, ObservableValue> getValueFactory( final int day ){
         return new Callback<TableColumn.CellDataFeatures<AttendanceEmployeeVM,AttendanceEmployeeVM>, ObservableValue>(  ) {
             @Override
             public ObservableValue call(TableColumn.CellDataFeatures<AttendanceEmployeeVM,AttendanceEmployeeVM> param) {
                 return new ReadOnlyObjectWrapper<>(param.getValue());
             }
         };
    }

    public static Callback<TableColumn, TableCell> FACTORY = new Callback<TableColumn, TableCell>(){
        @Override
        public TableCell call(TableColumn data) {
            return new EmployeeCell( data );
        }
    };

    private TableColumn column;

    public EmployeeCell(TableColumn column) {
        this.column = column;
    }

    @Override
    protected void updateItem(AttendanceEmployeeVM item, boolean empty) {
        if(item!=null){
            HBox hbox = new HBox();
            hbox.setPadding(new Insets(5));
            hbox.setMinWidth(180);
            hbox.setSpacing(10);
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(ClassLoader.getSystemClassLoader().getResourceAsStream( "image/avatar.jpg") ) );
            imageView.setFitHeight(34);
            imageView.setFitWidth(34);
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER_LEFT);
            Label nameLabel = new Label(item.name);
            nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            vbox.getChildren().add(nameLabel);
            Label hoursLabel = new Label(item.hours);
            hoursLabel.setPadding(new Insets(5,0,0,0));
            hoursLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
            vbox.getChildren().add(hoursLabel);
            hbox.getChildren().add(imageView);
            hbox.getChildren().add(vbox);
            setGraphic(hbox);
        }
    }
}
