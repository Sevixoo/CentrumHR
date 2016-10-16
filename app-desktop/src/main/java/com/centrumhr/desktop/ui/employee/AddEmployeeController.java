package com.centrumhr.desktop.ui.employee;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.presenter.AddEmployeePresenter;
import com.centrumhr.data.model.Employee;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 27.09.2016.
 */
public class AddEmployeeController extends Controller implements AddEmployeePresenter.View {


    @FXML TextField inputFirstName;
    @FXML TextField inputLastName;
    @FXML TextField inputCode;

    @FXML Button buttonSubmit;
    @FXML Label messageLabel;

    Employee employeeToEdit;
    UUID employeeToEditID;

    @Inject
    public AddEmployeePresenter mPresenter;

    private List<Employee> mData = new ArrayList<>();

    public AddEmployeeController() {
        super("layout/employee_add_employee_scene.fxml");
    }

    @Override
    public void initialize() {
        initializeInjection();
        mPresenter.setView(this);
        buttonSubmit.setOnAction( event -> submit() );
        setResult(RESULT_CANCEL);
        if(employeeToEditID!=null){
            mPresenter.loadEmployee( employeeToEditID );
        }
        refreshForm();
    }

    private void initializeInjection(){
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
    }

    private boolean isFormValid(){
        return !inputFirstName.getText().isEmpty() && !inputLastName.getText().isEmpty() && !inputCode.getText().isEmpty();
    }

    public void editEmployee(UUID employeeID){
        employeeToEditID = employeeID;
    }

    public void editEmployee(Employee employee){
        employeeToEdit = employee;
        if(mPresenter!=null){
            refreshForm();
        }
    }

    private void refreshForm(){
        if(employeeToEdit==null){
            clearForm();
        }else{
            inputFirstName.setText(employeeToEdit.getFirstName());
            inputLastName.setText(employeeToEdit.getSurname());
            inputCode.setText(employeeToEdit.getCode());
        }
    }

    private void clearForm(){
        inputFirstName.setText("");
        inputLastName.setText("");
        inputCode.setText("");
    }

    private void submit(){
        if(isFormValid()){
            String firstName =inputFirstName.getText();
            String lastName =inputLastName.getText();
            String code =inputCode.getText();
            Employee employee = new Employee(firstName,lastName,code);
            if(employeeToEdit==null){
                mPresenter.addEmployee( employee );
            }else{
                mPresenter.editEmployee( employeeToEdit , employee );
            }
        }
    }

    public List<Employee> getResultData(){
        return mData;
    }

    @Override
    public void showProgress(Message message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void dismiss() {
        finish();
    }

    @Override
    public void displayError(String message) {
        messageLabel.setText(message);
    }

    @Override
    public void displayAddEmployeeSuccess(Employee employee) {
        setResult(RESULT_OK);
        mData.add(employee);
        clearForm();
        messageLabel.setText("Dodano nowego pracownika");
    }
}
