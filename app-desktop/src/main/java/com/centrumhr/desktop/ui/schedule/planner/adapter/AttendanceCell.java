package com.centrumhr.desktop.ui.schedule.planner.adapter;

import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import com.centrumhr.desktop.ui.schedule.data.AttendanceEmployeeVM;
import com.centrumhr.desktop.ui.schedule.planner.AttendancePlanPickerController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

/**
 * Created by Seweryn on 16.10.2016.
 */
public class AttendanceCell extends TableCell<AttendanceEmployeeVM,AttendanceDayVM>{

    public static Callback<TableColumn.CellDataFeatures<AttendanceEmployeeVM,AttendanceDayVM>, ObservableValue> getValueFactory( final int day ){
         return param -> param.getValue().data.get(day);
    }

    public static class AttendanceCellFactory implements Callback<TableColumn, TableCell>{

        public interface OnSelectCell{
            void onSelectCell( TableCell tableCell );
        }

        private OnSelectCell onSelectCell;
        private EventHandler<? super MouseEvent> onClick;

        public AttendanceCellFactory( EventHandler<? super MouseEvent> onClick , OnSelectCell onSelectCell ) {
            this.onClick = onClick;
            this.onSelectCell = onSelectCell;
        }

        @Override
        public TableCell call(TableColumn data) {
            TableCell cell = new AttendanceCell( data );
            cell.setOnMouseClicked( onClick );

            cell.focusedProperty().addListener( (observable, oldValue, newValue) -> {
                if(newValue){
                    onSelectCell.onSelectCell(cell);
                }
            });
            return cell;
        }
    };

    private TableColumn column;

    public AttendanceCell(TableColumn column) {
        this.column = column;
    }

    @Override
    protected void updateItem(AttendanceDayVM item, boolean empty) {

        if(item!=null){
            setPadding(new Insets(2,2,1,2));
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(5));
            vbox.setStyle("-fx-background-color: "+item.color+";-fx-background-radius: 0;");
            vbox.setAlignment(Pos.CENTER_LEFT);
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            Label label = new Label(item.label);
            label.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
            Label label2 = new Label(item.label2);
            label2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
            hBox.getChildren().add(label);
            hBox.getChildren().add(label2);
            vbox.getChildren().add(hBox);
            setGraphic(vbox);
        }

    }
}
