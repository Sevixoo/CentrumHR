package com.centrumhr.desktop.ui.schedule.creator;

import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import com.centrumhr.desktop.ui.schedule.data.AttendancePlanVM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
import java.util.stream.Collectors;

/**
 * Created by Seweryn on 05.11.2016.
 */
public class AddScheduleController extends Controller implements AddSchedulePresenter.View {

    @FXML Label messageLabel;
    @FXML TextField inputName;
    @FXML DatePicker beginDatePicker;
    @FXML Button buttonOK;
    @FXML Pane employeeListContainer;

    @Inject AddSchedulePresenter presenter;

    private CheckListView<EmployeeVM> employeeCheckListView;


    private AttendancePlanVM data;

    public AddScheduleController() {
        super("layout/schedule_add_plan_dialog.fxml");
        setTitle("Add Plan Dialog");
    }

    @Override
    public void initialize() {
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
        presenter.setView(this);
        buttonOK.setOnAction(event -> submit() );
        presenter.loadEmployeesList();
    }

    @Override
    public void onStageCreated(Stage stage) {
        stage.resizableProperty().setValue(Boolean.FALSE);
    }

    private void submit(){
        String name = inputName.getText();
        Date  beginDate = getBeginDateDate();
        List<UUID> employees = getSelectedEmployees();
        String error = validate( name , beginDate , employees );
        if( error == null ){
            presenter.createAttendancePlan( name , beginDate , employees );
        }else{
            messageLabel.setText(error);
        }
    }

    private String validate(String name , Date beginDate , List<UUID> employees){
        if(name == null || name.equals(""))return "Podaj nazwe.";
        if( beginDate == null )return "Padaj date rozpoczêcia.";
        if(employees.size() == 0 )return "Padaj przynajmniej jednego pracownika";
        return null;
    }

    private List<UUID> getSelectedEmployees(){
        return employeeCheckListView.getCheckModel().getSelectedItems().stream()
                .map(EmployeeVM::getUuid)
                .collect(Collectors.toList());
    }

    @Override
    public void displayEmployeesList(List<EmployeeVM> employeeVMs) {
        final ObservableList<EmployeeVM> data = FXCollections.observableArrayList();
        data.addAll(employeeVMs);
        employeeCheckListView = new CheckListView<>(data);
        employeeListContainer.getChildren().add(employeeCheckListView);
        employeeCheckListView.prefWidthProperty().bind(employeeListContainer.widthProperty());
        employeeCheckListView.prefHeightProperty().bind(employeeListContainer.heightProperty());
        employeeCheckListView.setItems(data);
    }

    @Override
    public void onAttendancePlanCreated(AttendancePlanVM attendancePlanVM) {
        setResult(RESULT_OK);
        data = attendancePlanVM;
        dismiss();
    }

    private Date getBeginDateDate(){
        LocalDate localDate = beginDatePicker.getValue();
        if(localDate==null)return null;
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    public AttendancePlanVM getData() {
        return data;
    }

}
