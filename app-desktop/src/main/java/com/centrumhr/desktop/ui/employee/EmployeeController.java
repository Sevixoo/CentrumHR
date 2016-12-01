package com.centrumhr.desktop.ui.employee;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.presenter.EmployeePresenter;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.common.SimpleAskDialog;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Seweryn on 27.09.2016.
 */
public class EmployeeController extends Controller implements EmployeePresenter.View {

    @FXML Pane container;
    @FXML Button addEmployeeButton;
    @FXML Button editEmployeeButton;
    @FXML Button deleteEmployeeButton;

    @Inject EmployeePresenter mPresenter;

    EmployeeListController employeeListController;

    public EmployeeController() {
        super("layout/employee_controller.fxml");
    }

    @FXML public void initialize(){
        initializeInjection();
        mPresenter.setView(this);
        editEmployeeButton.setDisable(true);
        deleteEmployeeButton.setDisable(true);
        addEmployeeButton.setOnAction( event -> displayAddEmployeeScreen() );
        editEmployeeButton.setOnAction( event -> displayEditEmployeeScreen() );
        deleteEmployeeButton.setOnAction(event -> displayDeleteEmployeeDialog() );
        displayEmployeeList();
    }

    private void initializeInjection(){
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
    }

    private void onSelectEmployee(EmployeeVM employeeVM){
        if(employeeVM == null) {
            editEmployeeButton.setDisable(true);
            deleteEmployeeButton.setDisable(true);
        }else{
            editEmployeeButton.setDisable(false);
            deleteEmployeeButton.setDisable(false);
        }
    }

    public void displayEmployeeList(){
        employeeListController = new EmployeeListController();
        employeeListController.setListener( this::onSelectEmployee );
        displayComponent( container , employeeListController ); 
    }

    public void displayDeleteEmployeeDialog(){
        SimpleAskDialog dialog = new SimpleAskDialog("Usunąć pracownika?");
        dialog.startForResult( this );
        if(dialog.getResult() == RESULT_OK){
            mPresenter.deleteEmployee( employeeListController.getSelectedEmployeeID() );
        }
    }

    public void displayEditEmployeeScreen(){
        AddEmployeeController dialog = new AddEmployeeController();
        dialog.editEmployee( employeeListController.getSelectedEmployeeID() );
        dialog.startForResult( this );
        if(dialog.getResult() == RESULT_OK){
            refreshListData(dialog.getResultData());
        }
    }

    public void displayAddEmployeeScreen() {
        AddEmployeeController dialog = new AddEmployeeController();
        dialog.startForResult( this );
        if(dialog.getResult() == RESULT_OK){
            refreshListData(dialog.getResultData());
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
    public void onUserDeleted() {
        displayEmployeeList();
    }

    private void refreshListData(List<Employee> employees){
        for( Employee employee : employees ){
            employeeListController.displayAddedEmployee(employee);
        }
    }

}
