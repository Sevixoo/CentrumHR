package com.centrumhr.desktop.ui.employee;

import com.centrumhr.application.employee.dto.PersonalDataDTO;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import com.centrumhr.desktop.ui.employee.data.WorkFunctionVM;
import com.centrumhr.desktop.ui.employee.presenter.AddEmployeePresenter;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.settings.department.data.DepartmentVM;
import com.centrumhr.dto.employment.WorkTime;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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

    @FXML Pane departmentsContainer;

    CheckListView<String> departmentCheckListView;

    @FXML RadioButton rb1;
    @FXML RadioButton rb2;
    @FXML RadioButton rb3;
    @FXML RadioButton rb4;

    EmployeeVM employeeToEdit;
    UUID employeeToEditID;

    @Inject
    public AddEmployeePresenter presenter;

    private List<EmployeeVM> employees = new ArrayList<>();
    private List<DepartmentVM> departments = new ArrayList<>();
    private List<WorkFunctionVM> workFunctions = new ArrayList<>();

    private Runnable onSuccessListener;

    public AddEmployeeController() {
        super("layout/employee_add_employee_scene.fxml");
        setTitle("Add EmployeeEntity Dialog");
    }

    @Override
    public void onStageCreated(Stage stage) {
        stage.resizableProperty().setValue(Boolean.FALSE);
    }

    @Override
    public void initialize() {
        initializeInjection();
        presenter.setView(this);
        buttonSubmit.setVisible(employeeToEditID==null);
        buttonSubmit.setOnAction( event -> submit() );
        buttonOK.setOnAction(event -> {
            submit();
            onSuccessListener = this::dismiss;
        });
        setResult(RESULT_CANCEL);
        if(employeeToEditID!=null){
            presenter.loadEmployee( employeeToEditID );
        }

        employmentDatePicker.setValue(null);
        judgmentCheckBox.setSelected(false);

        presenter.loadDepartments();
        presenter.loadWorkFunctions();

        messageLabel.setText("");

        refreshForm();
    }

    private void initializeInjection(){
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
    }

    private boolean isFormValid(){
        if(inputFirstName.getText().isEmpty()){
            messageLabel.setText("Wpisz imi?");
            return false;
        }
        if(inputLastName.getText().isEmpty()){
            messageLabel.setText("Wpisz nazwisko");
            return false;
        }
        if(inputCode.getText().isEmpty()){
            messageLabel.setText("Wpisz kod pracownika");
            return false;
        }
        if(functionChoiceBox.getSelectionModel().getSelectedItem()==null){
            messageLabel.setText("Wybierz pe?nion? funkcje");
            return false;
        }
        if(employmentDatePicker.getValue()==null){
            messageLabel.setText("Wybierz dat? zatrudnienia");
            return false;
        }
        if(getWorkTime()==null){
            messageLabel.setText("Wybierz etat");
            return false;
        }
        return true;
    }

    @Override
    public void departmentLoaded(List<DepartmentVM> data) {
        departments.clear();
        departments.addAll(data);
        final ObservableList<String> strings = FXCollections.observableArrayList();
        for (DepartmentVM department  : departments) {
            strings.add(department.getName());
        }
        departmentCheckListView = new CheckListView<>(strings);
        departmentCheckListView.prefWidthProperty().bind(departmentsContainer.widthProperty());
        departmentCheckListView.prefHeightProperty().bind(departmentsContainer.heightProperty());
        departmentsContainer.getChildren().add(departmentCheckListView);
        refreshForm();
    }

    @Override
    public void workFunctionsLoaded(List<WorkFunctionVM> data) {
        workFunctions.clear();
        workFunctions.addAll(data);
        ObservableList<String> objectsObservableList = FXCollections.observableArrayList();;
        for (WorkFunctionVM workFunctionEntity : workFunctions) {
            objectsObservableList.add(workFunctionEntity.getName()) ;
        }
        functionChoiceBox.setItems( objectsObservableList );
        refreshForm();
    }

    public void editEmployee(UUID employeeID){
        setTitle("Edit EmployeeEntity Dialog Box");
        employeeToEditID = employeeID;
    }

    public void editEmployee(EmployeeVM employee ){
        employeeToEdit = employee;
        if(presenter!=null){
            refreshForm();
        }
    }

    private void refreshForm(){
        if(employeeToEdit ==null){
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
            employmentDatePicker
                    .setValue( employeeToEdit.getEmploymentDate()
                    .toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDate()
            );

            if(workFunctions.size() > 0) {
                for (int i = 0; i < workFunctions.size(); i++) {
                    if (workFunctions.get(i).equals(employeeToEdit.getWorkFunction())) {
                        functionChoiceBox.getSelectionModel().select(i);
                    }
                }
            }

            if(departments.size() > 0) {
                List<Integer> integers = new ArrayList<>();
                for (int i = 0; i < departments.size(); i++) {
                    for (DepartmentVM departmentVM : employeeToEdit.getDepartments()) {
                        if (departmentVM.getUuid().equals(departments.get(i).getUuid())) {
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
        if(departmentCheckListView!=null) {
            departmentCheckListView.getCheckModel().clearSelection();
            departmentCheckListView.getSelectionModel().clearSelection();
        }
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
            WorkFunctionVM workFunction  = workFunctions.get(functionChoiceBox.getSelectionModel().getSelectedIndex());
            List<DepartmentVM> department = getSelectedDepartments();

            Date employmentDate = getEmploymentDate();
            boolean isJudgment = judgmentCheckBox.isSelected();
            WorkTime workTime = getWorkTime();

            if(employeeToEdit ==null){
                presenter.addEmployee( personalData , workFunction, department, employmentDate , isJudgment , workTime );
            }else{
                presenter.editEmployee( employeeToEditID, personalData , workFunction, department, employmentDate , isJudgment , workTime );
            }
        }
    }

    private List<DepartmentVM> getSelectedDepartments(){
        return departmentCheckListView.getCheckModel().getSelectedItems().stream()
                .map( s -> departments.stream()
                        .filter( departmentVM -> departmentVM.getName().equals(s))
                        .findFirst().get()
                ).collect(Collectors.toList());
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

    public List<EmployeeVM> getResultData(){
        return employees;
    }

    @Override
    public void dismiss() {
        finish();
    }

    @Override
    public void displayAddEmployeeSuccess(EmployeeVM employeeVM) {
        setResult(RESULT_OK);
        employees.add(employeeVM);
        clearForm();
        messageLabel.setText("Dodano nowego pracownika");
        if(onSuccessListener!=null){
            onSuccessListener.run();
        }
    }

}
