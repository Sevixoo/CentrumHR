package com.centrumhr.desktop.ui.schedule.planner.adapter;

import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendanceEmployee;
import com.centrumhr.desktop.core.NullTableViewSelectionModel;
import com.centrumhr.desktop.ui.employee.adapter.EmployeeTableAdapter;
import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import com.centrumhr.desktop.ui.schedule.data.AttendanceEmployeeVM;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

    public interface Callback{
        void onAttendanceDayClick( AttendanceDayVM dayVM , TableCell tableCell );
        void onEmployeeClick( AttendanceEmployeeVM employeeVM );
    }

    public enum VIEW_TYPE{
        WEEK
    }

    private Callback mListener;
    private TableCell mSelectedTableCell;
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

        for (int i = 0 ; i < 7 ; i++){
            int col = i + 1;
            tableColumns[col] = new TableColumn("Day " + (i));
            tableColumns[col].setSortable(false);
            tableColumns[col].setResizable(false);
            tableColumns[col].setEditable(false);
            tableColumns[col].setCellFactory( new AttendanceCell.AttendanceCellFactory(onClickAttendanceCell, new AttendanceCell.AttendanceCellFactory.OnSelectCell() {
                @Override
                public void onSelectCell(TableCell tableCell) {
                    mSelectedTableCell = tableCell;
                }
            }));
            tableColumns[col].setCellValueFactory( AttendanceCell.getValueFactory(i) );
        }


        return tableColumns;
    }

    private EventHandler<? super MouseEvent> onClickAttendanceCell = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            TableCell cell = (TableCell)event.getSource();
            int coll = mTableView.getSelectionModel().getSelectedCells().get(0).getColumn();
            int row = mTableView.getSelectionModel().getSelectedCells().get(0).getRow();
            AttendanceDayVM attendanceDayVM = mData.get(row).data.get(coll-1).getValue();
            if(mListener!=null){
                mListener.onAttendanceDayClick( attendanceDayVM , cell );
            }
        }
    };

    public TableCell getSelectedTableCell() {
        return mSelectedTableCell;
    }

    public Object getSelectedItem(){
        int coll = mTableView.getSelectionModel().getSelectedCells().get(0).getColumn();
        int row = mTableView.getSelectionModel().getSelectedCells().get(0).getRow();
        if(coll>0) {
            return mData.get(row).data.get(coll - 1).getValue();
        }else{
            return mData.get(row);
        }
    }

    public void setData( Collection<AttendanceEmployee> attendanceEmployees ){
        List<AttendanceEmployeeVM> list = new ArrayList<>();
        for (AttendanceEmployee attendance : attendanceEmployees) {
            list.add( new AttendanceEmployeeVM(attendance) );
        }
        mData.clear();
        mData.addAll(list);
    }

    public void refresh( AttendanceDayVM attendanceDayVM ){
        System.err.println("[AttendanceDayVM]"+ attendanceDayVM.attendanceDayUniqueId.toString() );
        for (int i = 0; i < mData.size(); i++) {
            if(mData.get(i).uniqueId.equals(attendanceDayVM.employeeUniqueId)){
                AttendanceEmployeeVM attendanceEmployeeVM = mData.get(i);
                for (int j = 0; j < attendanceEmployeeVM.data.size(); j++) {
                    if( attendanceEmployeeVM.data.get(j).getValue().attendanceDayUniqueId.equals(attendanceDayVM.attendanceDayUniqueId) ){
                        attendanceEmployeeVM.data.set(j , new ReadOnlyObjectWrapper<>(attendanceDayVM));
                        mTableView.refresh();
                        return;
                    }
                }
            }
        }
        throw new RuntimeException("ex");
    }

    public void setListener( Callback listener ){
        mListener = listener;
    }

    public AttendanceEmployeeVM getItem(int position){
        return mData.get(position);
    }


}
