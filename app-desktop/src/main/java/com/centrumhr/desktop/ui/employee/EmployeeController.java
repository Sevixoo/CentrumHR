package com.centrumhr.desktop.ui.employee;

import com.centrumhr.data.model.Employee;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Created by Seweryn on 27.09.2016.
 */
public class EmployeeController extends Controller {

    @FXML Pane container;
    @FXML Button addEmployeeButton;
    @FXML Button editEmployeeButton;
    @FXML Button deleteEmployeeButton;

    EmployeeListController employeeListController;

    public EmployeeController() {
        super("layout/employee_controller.fxml");
    }

    @FXML public void initialize(){
        editEmployeeButton.setDisable(true);
        deleteEmployeeButton.setDisable(true);
        addEmployeeButton.setOnAction( event -> displayAddEmployeeScreen() );
        editEmployeeButton.setOnAction( event -> displayEditEmployeeScreen() );
        deleteEmployeeButton.setOnAction(event -> displayDeleteEmployeeDialog() );
        displayEmployeeList();
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

    private void refreshListData(List<Employee> employees){
        for( Employee employee : employees ){
            employeeListController.displayAddedEmployee(employee);
        }
    }

}
