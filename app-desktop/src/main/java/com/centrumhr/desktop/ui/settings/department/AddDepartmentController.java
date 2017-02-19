package com.centrumhr.desktop.ui.settings.department;

import com.centrumhr.desktop.ui.settings.department.data.DepartmentVM;
import com.centrumhr.desktop.ui.settings.department.presenter.AddDepartmentPresenter;
import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seweryn on 27.09.2016.
 */
public class AddDepartmentController extends Controller implements AddDepartmentPresenter.View {

    @FXML TextField inputName;

    @FXML Button buttonSubmit;
    @FXML Label messageLabel;

    @Inject AddDepartmentPresenter mPresenter;

    private List<DepartmentVM> mData = new ArrayList<>();

    public AddDepartmentController() {
        super("layout/settings_department_add_department_scene.fxml");
    }

    @Override
    public void initialize() {
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
        mPresenter.setView(this);
        buttonSubmit.setOnAction( event -> submit() );
        setResult(RESULT_CANCEL);
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

    List<DepartmentVM> getResultData(){
        return mData;
    }

    @Override
    public void onAddDepartmentSuccess(DepartmentVM department ) {
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
