package com.centrumhr.desktop.ui.employee;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.presenter.EmployeeListPresenter;
import com.centrumhr.data.model.Employee;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.employee.adapter.EmployeeTableAdapter;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 27.09.2016.
 */
public class EmployeeListController extends Controller implements  EmployeeListPresenter.View , EmployeeTableAdapter.Callback{

    public interface Callback{
        void onSelectItem( EmployeeVM employeeVM );
    }

    private Callback mListener;

    @Inject
    public EmployeeListPresenter mPresenter;

    @FXML TableView<EmployeeVM> mEmployeeTableView;

    private EmployeeTableAdapter mEmployeeTableAdapter;

    public EmployeeListController() {
        super("layout/employee_list_scene.fxml");
    }

    @Override
    public void initialize() {
        initializeInjection();
        mPresenter.setView(this);
        mEmployeeTableAdapter = new EmployeeTableAdapter(mEmployeeTableView);
        mEmployeeTableAdapter.setListener(this);
        mPresenter.loadEmployees();
    }

    public UUID getSelectedEmployeeID(){
        return mEmployeeTableAdapter.getSelectedEmployeeID();
    }

    public void setListener(Callback mListener) {
        this.mListener = mListener;
    }

    private void initializeInjection(){
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
    }

    @Override
    public void onSelectItem(EmployeeVM employeeVM) {
        if(mListener!=null){
            mListener.onSelectItem(employeeVM);
        }
    }

    @Override
    public void showProgress(Message message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public void displayAddedEmployee(Employee employee) {
        mEmployeeTableAdapter.addData(employee);
    }

    @Override
    public void displayEmployeeList(List<Employee> employees) {
        mEmployeeTableAdapter.setData(employees);
    }
}
