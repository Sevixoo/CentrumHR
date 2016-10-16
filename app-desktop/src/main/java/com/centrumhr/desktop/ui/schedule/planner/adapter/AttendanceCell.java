package com.centrumhr.desktop.ui.schedule.planner.adapter;

import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import com.centrumhr.desktop.ui.schedule.data.AttendanceEmployeeVM;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * Created by Seweryn on 16.10.2016.
 */
public class AttendanceCell extends TableCell<AttendanceEmployeeVM,AttendanceDayVM>{

    public static Callback<TableColumn.CellDataFeatures<AttendanceEmployeeVM,AttendanceDayVM>, ObservableValue> getValueFactory( final int day ){
         return new Callback<TableColumn.CellDataFeatures<AttendanceEmployeeVM,AttendanceDayVM>, ObservableValue>(  ) {
             @Override
             public ObservableValue call(TableColumn.CellDataFeatures<AttendanceEmployeeVM,AttendanceDayVM> param) {
                 return param.getValue().data.get(day);
             }
         };
    }

    public static Callback<TableColumn, TableCell> FACTORY = new Callback<TableColumn, TableCell>(){
        @Override
        public TableCell call(TableColumn data) {
            return new AttendanceCell( data );
        }
    };

    private TableColumn column;

    public AttendanceCell(TableColumn column) {
        this.column = column;
    }

    @Override
    protected void updateItem(AttendanceDayVM item, boolean empty) {

        if(item!=null){
            VBox vbox = new VBox();
            vbox.getChildren().add(new Label("aaa"));
            vbox.getChildren().add(new Label("bbb"));
            setGraphic(vbox);
        }else{
            System.err.println("aaaa");
        }

    }
}
