package com.centrumhr.desktop.ui.settings.department.adapter;

import com.centrumhr.data.model.employment.Department;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import com.centrumhr.desktop.ui.settings.department.data.DepartmentVM;
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
public class DepartmentTableAdapter {

    public interface Callback{
        void onSelectItem(DepartmentVM departmentVM);
    }

    private Callback mListener;

    private TableView<DepartmentVM> mTableView;
    private ObservableList<DepartmentVM> mData;

    public DepartmentTableAdapter(TableView<DepartmentVM> tableView) {
        mTableView = tableView;
        ArrayList<DepartmentVM> list = new ArrayList<>();
        mData = FXCollections.observableList(list);
        mTableView.setItems(mData);

        TableColumn nameCol = new TableColumn("Nazwa");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        mTableView.getColumns().setAll(nameCol);
        mTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        mTableView.getSelectionModel().selectedIndexProperty().addListener(  new RowSelectChangeListener());
        mTableView.getSelectionModel().select(-1);
    }

    public void addData(Department department){
        mData.add(new DepartmentVM(department));
    }

    public UUID getSelectedDepartmentID(){
        DepartmentVM departmentVM = mTableView.getSelectionModel().getSelectedItem();
        if(departmentVM==null)return null;
        return departmentVM.getUuid();
    }

    public void setData(List<Department> departments){
        List<DepartmentVM> list = new ArrayList<>();
        for (Department department : departments) {
            list.add( new DepartmentVM(department) );
        }
        mData.clear();
        mData.addAll(list);
    }

    public DepartmentVM getItem(int position){
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
