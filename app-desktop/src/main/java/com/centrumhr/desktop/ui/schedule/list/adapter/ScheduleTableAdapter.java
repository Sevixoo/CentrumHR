package com.centrumhr.desktop.ui.schedule.list.adapter;

import com.centrumhr.desktop.ui.schedule.data.ScheduleVM;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class ScheduleTableAdapter {

    public interface Callback{
        void onSelectItem(ScheduleVM scheduleVM);
    }

    private Callback mListener;

    private TableView<ScheduleVM> mTableView;
    private ObservableList<ScheduleVM> mData;

    public ScheduleTableAdapter(TableView<ScheduleVM> tableView) {
        mTableView = tableView;
        ArrayList<ScheduleVM> list = new ArrayList<>();
        mData = FXCollections.observableList(list);
        mTableView.setItems(mData);

        TableColumn nameCol = new TableColumn("Nazwa");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn codeCol = new TableColumn("Data dodania");
        codeCol.setCellValueFactory(new PropertyValueFactory("createDate"));
        TableColumn stateCol = new TableColumn("Stan");
        stateCol.setCellValueFactory(new PropertyValueFactory("state"));

        mTableView.getColumns().setAll(nameCol, codeCol,stateCol);
        mTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        mTableView.getSelectionModel().selectedIndexProperty().addListener(  new RowSelectChangeListener());
        mTableView.getSelectionModel().select(-1);
    }

    public void addData(ScheduleVM scheduleVM ){
        for (int i = 0; i < mData.size(); i++) {
            if(mData.get(i).equals(scheduleVM)){
                mData.set(i,scheduleVM);
                return;
            }
        }
        mData.add(scheduleVM);
    }

    public UUID getSelectedID(){
        ScheduleVM scheduleVM = mTableView.getSelectionModel().getSelectedItem();
        if(scheduleVM==null)return null;
        return scheduleVM.getUuid();
    }

    public void setData(List<ScheduleVM> data){
        mData.clear();
        mData.addAll(data);
    }

    public ScheduleVM getItem(int position){
        return mData.get(position);
    }

    public void setListener(Callback mListener) {
        this.mListener = mListener;
    }

    private class RowSelectChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue ov, Number oldVal, Number newVal) {
            int ix = newVal.intValue();
            if ((ix == mData.size())) {
                return; // invalid data
            }
            if(mListener!=null){
                mListener.onSelectItem(mTableView.getSelectionModel().getSelectedItem());
            }
        }
    }


}
