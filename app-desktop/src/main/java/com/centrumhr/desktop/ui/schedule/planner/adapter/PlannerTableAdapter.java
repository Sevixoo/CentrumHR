package com.centrumhr.desktop.ui.schedule.planner.adapter;

import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendanceEmployee;
import com.centrumhr.desktop.core.NullTableViewSelectionModel;
import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import com.centrumhr.desktop.ui.schedule.data.AttendanceEmployeeVM;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class PlannerTableAdapter {

    public enum VIEW_TYPE{
        WEEK
    }

    private TableView<AttendanceEmployeeVM> mTableView;
    private ObservableList<AttendanceEmployeeVM> mData;

    public PlannerTableAdapter(TableView<AttendanceEmployeeVM> tableView) {
        mTableView = tableView;
        ArrayList<AttendanceEmployeeVM> list = new ArrayList<>();
        mData = FXCollections.observableList(list);
        mTableView.setItems(mData);
        mTableView.getColumns().setAll(createColumns(VIEW_TYPE.WEEK));
        mTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        mTableView.getSelectionModel().clearSelection();
        mTableView.getSelectionModel().setCellSelectionEnabled(true);
        mTableView.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) {
                TableHeaderRow header = (TableHeaderRow) tableView.lookup("TableHeaderRow");
                header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        header.setReordering(false);
                    }
                });
            }
        });

    }

    public TableColumn[] createColumns( VIEW_TYPE viewType ){
        TableColumn[] tableColumns = new TableColumn[8];

        tableColumns[0] = new TableColumn("Employee");
        tableColumns[0].setMinWidth(200);
        tableColumns[0].setSortable(false);
        tableColumns[0].setResizable(false);
        tableColumns[0].setEditable(false);
        tableColumns[0].setCellFactory( EmployeeCell.FACTORY );
        tableColumns[0].setCellValueFactory( EmployeeCell.getValueFactory(0) );

        for (int i = 1 ; i < 8 ; i++){
            tableColumns[i] = new TableColumn("Day " + (i));
            tableColumns[i].setSortable(false);
            tableColumns[i].setResizable(false);
            tableColumns[i].setEditable(false);
            tableColumns[i].setCellFactory( AttendanceCell.FACTORY );
            tableColumns[i].setCellValueFactory( AttendanceCell.getValueFactory(i) );
        }

        return tableColumns;
    }


    public void setData( Collection<AttendanceEmployee> attendanceEmployees ){
        List<AttendanceEmployeeVM> list = new ArrayList<>();
        for (AttendanceEmployee attendance : attendanceEmployees) {
            list.add( new AttendanceEmployeeVM(attendance) );
        }
        mData.clear();
        mData.addAll(list);
    }

    public AttendanceEmployeeVM getItem(int position){
        return mData.get(position);
    }


}
