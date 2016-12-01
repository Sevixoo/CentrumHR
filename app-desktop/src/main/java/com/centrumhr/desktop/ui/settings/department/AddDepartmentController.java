package com.centrumhr.desktop.ui.settings.department;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.presenter.AddEmployeePresenter;
import com.centrumhr.application.presenter.department.AddDepartmentPresenter;
import com.centrumhr.application.presenter.department.DepartmentPresenter;
import com.centrumhr.data.model.employment.Department;
import com.centrumhr.data.model.employment.Employee;
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
public class AddDepartmentController extends Controller implements AddDepartmentPresenter.View {

    @FXML TextField inputName;

    @FXML Button buttonSubmit;
    @FXML Label messageLabel;

    @Inject
    public AddDepartmentPresenter mPresenter;

    private List<Department> mData = new ArrayList<>();

    public AddDepartmentController() {
        super("layout/settings_department_add_department_scene.fxml");
    }

    @Override
    public void initialize() {
        initializeInjection();
        mPresenter.setView(this);
        buttonSubmit.setOnAction( event -> submit() );
        setResult(RESULT_CANCEL);
    }

    private void initializeInjection(){
        mPresenter = CentrumHRApplication.getInstance().getLoggedAccountComponent().getAddDepartmentPresenter();
    }

    private boolean isFormValid(){
        return !inputName.getText().isEmpty();
    }

    private void submit(){
        if(isFormValid()){
            String name =inputName.getText();
            mPresenter.addDepartment( name );
        }
    }

    public List<Department> getResultData(){
        return mData;
    }

    @Override
    public void onAddDepartmentSuccess(Department department) {
        mData.add(department);
        setResult(RESULT_OK);
        dismiss();
    }

    @Override
    public void dismiss() {
        finish();
    }

    @Override
    public void displayError(String message) {
        messageLabel.setText(message);
    }

}
