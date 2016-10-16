package com.centrumhr.desktop.ui.employee.adapter;

import com.centrumhr.data.model.Employee;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
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
public class EmployeeTableAdapter {

    public interface Callback{
        void onSelectItem( EmployeeVM employeeVM );
    }

    private Callback mListener;


    private TableView<EmployeeVM> mTableView;
    private ObservableList<EmployeeVM> mData;

    public EmployeeTableAdapter(TableView<EmployeeVM> tableView) {
        mTableView = tableView;
        ArrayList<EmployeeVM> list = new ArrayList<>();
        mData = FXCollections.observableList(list);
        mTableView.setItems(mData);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn codeCol = new TableColumn("Code");
        codeCol.setCellValueFactory(new PropertyValueFactory("code"));

        mTableView.getColumns().setAll(nameCol, codeCol);
        mTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        mTableView.getSelectionModel().selectedIndexProperty().addListener(  new RowSelectChangeListener());
        mTableView.getSelectionModel().select(-1);
    }

    public void addData(Employee employee){
        mData.add(new EmployeeVM(employee));
    }

    public UUID getSelectedEmployeeID(){
        EmployeeVM employee = mTableView.getSelectionModel().getSelectedItem();
        if(employee==null)return null;
        return employee.getUuid();
    }

    public void setData(List<Employee> employees){
        List<EmployeeVM> list = new ArrayList<>();
        for (Employee employee : employees) {
            list.add( new EmployeeVM(employee) );
        }
        mData.clear();
        mData.addAll(list);
    }

    public EmployeeVM getItem(int position){
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
