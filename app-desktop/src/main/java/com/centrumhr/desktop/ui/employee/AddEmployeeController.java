package com.centrumhr.desktop.ui.employee;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.application.employee.dto.PersonalDataDTO;
import com.centrumhr.application.presenter.AddEmployeePresenter;
import com.centrumhr.data.model.employment.*;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.settings.department.data.DepartmentVM;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    @FXML Button buttonOK;
    @FXML Label messageLabel;
    @FXML ChoiceBox<String> functionChoiceBox;
    @FXML DatePicker employmentDatePicker;
    @FXML CheckBox judgmentCheckBox;
    @FXML CheckListView<String> departmentCheckListView;

    @FXML RadioButton rb1;
    @FXML RadioButton rb2;
    @FXML RadioButton rb3;
    @FXML RadioButton rb4;

    Employee employeeToEdit;
    UUID employeeToEditID;

    @Inject
    public AddEmployeePresenter mPresenter;

    private List<Employee> mData = new ArrayList<>();
    private List<Department> mDepartments = new ArrayList<>();
    private List<WorkFunction> mWorkFunctions = new ArrayList<>();

    public AddEmployeeController() {
        super("layout/employee_add_employee_scene.fxml");
        setTitle("Add Employee Dialog");
    }

    @Override
    public void onStageCreated(Stage stage) {
        stage.resizableProperty().setValue(Boolean.FALSE);
    }

    @Override
    public void initialize() {
        initializeInjection();
        mPresenter.setView(this);
        if(employeeToEditID==null){
            buttonSubmit.setText("Dodaj");
        }else{
            buttonSubmit.setText("Zapisz");
        }
        buttonSubmit.setOnAction( event -> submit() );
        buttonOK.setOnAction(event -> dismiss());
        setResult(RESULT_CANCEL);
        if(employeeToEditID!=null){
            mPresenter.loadEmployee( employeeToEditID );
        }

        employmentDatePicker.setValue(null);
        judgmentCheckBox.setSelected(false);

        mPresenter.loadDepartments();
        mPresenter.loadWorkFunctions();

        messageLabel.setText("");

        refreshForm();
    }

    private void initializeInjection(){
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
    }

    private boolean isFormValid(){
        return !inputFirstName.getText().isEmpty() && !inputLastName.getText().isEmpty() && !inputCode.getText().isEmpty();
    }

    @Override
    public void departmentLoaded(List<Department> data) {
        mDepartments.clear();
        mDepartments.addAll(data);
        final ObservableList<String> strings = FXCollections.observableArrayList();
        for (Department department : mDepartments) {
            strings.add(department.getName());
        }
        departmentCheckListView.setItems(strings);
        refreshForm();
    }

    @Override
    public void workFunctionsLoaded(List<WorkFunction> data) {
        mWorkFunctions.clear();
        mWorkFunctions.addAll(data);
        ObservableList<String> objectsObservableList = FXCollections.observableArrayList();;
        for (WorkFunction workFunction : mWorkFunctions) {
            objectsObservableList.add(workFunction.getName()) ;
        }
        functionChoiceBox.setItems( objectsObservableList );
        refreshForm();
    }

    public void editEmployee(UUID employeeID){
        setTitle("Edit Employee Dialog Box");

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
            switch (employeeToEdit.getWorkTime()){
                case FULL_TIME:rb1.setSelected(true);break;
                case PART_TIME:rb2.setSelected(true);break;
                case TIME_1div4:rb3.setSelected(true);break;
                case TIME_3div4:rb4.setSelected(true);break;
            }
            judgmentCheckBox.setSelected(employeeToEdit.isJudgment());
            if(mWorkFunctions.size() > 0) {
                for (int i = 0; i < mWorkFunctions.size(); i++) {
                    if (mWorkFunctions.get(i).equals(employeeToEdit.getWorkFunction())) {
                        functionChoiceBox.getSelectionModel().select(i);
                    }
                }
            }

            if(mDepartments.size() > 0) {
                List<Integer> integers = new ArrayList<>();
                for (int i = 0; i < mDepartments.size(); i++) {
                    for (EmployeeDepartment employeeDepartment : employeeToEdit.getDepartments()) {
                        if (employeeDepartment.getDepartment().equals(mDepartments.get(i))) {
                            integers.add(i);
                        }
                    }
                }
                int arr[] = new int[integers.size()];
                for (int i = 0; i < integers.size(); i++) arr[i] = integers.get(i);
                departmentCheckListView.getCheckModel().selectIndices(0, arr);
            }
        }
    }

    private void clearForm(){
        inputFirstName.setText("");
        inputLastName.setText("");
        inputCode.setText("");
        departmentCheckListView.getCheckModel().clearSelection();
        departmentCheckListView.getSelectionModel().clearSelection();
        functionChoiceBox.getSelectionModel().clearSelection();
        employmentDatePicker.setValue(null);
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
        judgmentCheckBox.setSelected(false);
    }

    private void submit(){
        if(isFormValid()){
            String firstName =inputFirstName.getText();
            String lastName =inputLastName.getText();
            String code =inputCode.getText();
            PersonalDataDTO personalData = new PersonalDataDTO( firstName, lastName, code );
            WorkFunction workFunction = mWorkFunctions.get(functionChoiceBox.getSelectionModel().getSelectedIndex());
            List<Department> departments = getSelectedDepartments();

            Date employmentDate = getEmploymentDate();
            boolean isJudgment = judgmentCheckBox.isSelected();
            WorkTime workTime = getWorkTime();

            if(employeeToEdit==null){
                mPresenter.addEmployee( personalData , workFunction , departments , employmentDate , isJudgment , workTime );
            }else{
                mPresenter.editEmployee( personalData , workFunction , departments , employmentDate , isJudgment , workTime , employeeToEdit );
            }
        }
    }

    private List<Department> getSelectedDepartments(){
        List<Department> list = new ArrayList<>();
        for (String item : departmentCheckListView.getCheckModel().getSelectedItems() ) {
            for (Department department : mDepartments) {
                if(item.equals(department.getName())){
                    list.add(department);
                }
            }
        }
        return list;
    }

    private Date getEmploymentDate(){
        LocalDate localDate = employmentDatePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    private WorkTime getWorkTime(){
        if( rb1.isSelected() )return WorkTime.FULL_TIME;
        if( rb2.isSelected() )return WorkTime.PART_TIME;
        if( rb3.isSelected() )return WorkTime.TIME_1div4;
        if( rb4.isSelected() )return WorkTime.TIME_3div4;
        return null;
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
