package com.centrumhr.desktop.ui.schedule.creator;

import com.centrumhr.application.presenter.shedule.AddAttendancePlanPresenter;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.employment.Department;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
 * Created by Seweryn on 05.11.2016.
 */
public class AddScheduleController extends Controller implements AddAttendancePlanPresenter.View {

    @FXML Label messageLabel;
    @FXML TextField inputName;
    @FXML DatePicker beginDatePicker;
    @FXML Button buttonOK;
    @FXML CheckListView<EmployeeVM> employeeCheckListView;

    @Inject AddAttendancePlanPresenter mPresenter;

    private AttendancePlan data;

    public AddScheduleController() {
        super("layout/schedule_add_plan_dialog.fxml");
        setTitle("Add Plan Dialog");
    }

    @Override
    public void initialize() {
        initializeInjection();
        mPresenter.setView(this);


        buttonOK.setOnAction(event -> submit() );
        mPresenter.loadEmployeesList();
    }

    private void submit(){
        String name = inputName.getText();
        Date  beginDate = getBeginDateDate();
        List<UUID> employees = getSelectedEmployees();
        String error = validate( name , beginDate , employees );
        if( error == null ){
            mPresenter.createAttendancePlan( name , beginDate , employees );
        }else{
            displayError(error);
        }
    }

    private String validate(String name , Date beginDate , List<UUID> employees){
        if(name == null || name.equals(""))return "Podaj nazwe.";
        if( beginDate == null )return "Padaj date rozpoczęcia.";
        //if(employees.size() == 0 )return "Padaj przynajmniej jednego pracownika";
        return null;
    }

    private List<UUID> getSelectedEmployees(){
        List<UUID> employees = new ArrayList<>();
        return employees;
    }

    @Override
    public void displayEmployeesList(List<Employee> employees) {
        final ObservableList<EmployeeVM> data = FXCollections.observableArrayList();
        for (Employee employee : employees) {
            data.add(new EmployeeVM(employee));
        }
        employeeCheckListView.setItems(data);
    }

    @Override
    public void onAttendancePlanCreated(AttendancePlan attendancePlan) {
        setResult(RESULT_OK);
        data = attendancePlan;
        dismiss();
    }

    @Override
    public void displayError(String message) {
        messageLabel.setText(message);
    }

    private Date getBeginDateDate(){
        LocalDate localDate = beginDatePicker.getValue();
        if(localDate==null)return null;
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    private void initializeInjection(){
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
    }

    public AttendancePlan getData() {
        return data;
    }

}
