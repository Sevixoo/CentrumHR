package com.centrumhr.desktop.ui.settings.department;

import com.centrumhr.desktop.ui.settings.department.presenter.DepartmentPresenter;
import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.settings.department.adapter.DepartmentTableAdapter;
import com.centrumhr.desktop.ui.settings.department.data.DepartmentVM;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class DepartmentSettingsController extends Controller implements  DepartmentPresenter.View {

    @FXML Button addDepartmentButton;
    @FXML Button deleteDepartmentButton;
    @FXML TableView<DepartmentVM> mDepartmentTableView;

    private DepartmentTableAdapter mAdapter;
    @Inject DepartmentPresenter mPresenter;

    public DepartmentSettingsController( ) {
        super("layout/settings_department_list_scene.fxml");
    }

    @Override
    public void initialize() {
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
        mPresenter.setView(this);
        mAdapter = new DepartmentTableAdapter(mDepartmentTableView);
        mAdapter.setListener(departmentVM -> refreshButtons());
        addDepartmentButton.setOnAction(event -> displayAddDepartmentDialog());
        deleteDepartmentButton.setOnAction(event -> deleteDepartment());
        mPresenter.loadDepartments();
        refreshButtons();
    }

    private void displayAddDepartmentDialog(){
        AddDepartmentController dialog = new AddDepartmentController();
        dialog.startForResult( this );
        if(dialog.getResult() == RESULT_OK){
            refreshListData(dialog.getResultData());
        }
    }

    private void refreshButtons(){
        if( mAdapter.getSelectedDepartmentID() == null ){
            deleteDepartmentButton.setDisable(true);
        }else{
            deleteDepartmentButton.setDisable(false);
        }
    }

    private void deleteDepartment(){
        mPresenter.deleteDepartment(mAdapter.getSelectedDepartmentID());
    }

    @Override
    public void displayDepartmentList(List<DepartmentVM> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onDepartmentDeleted(UUID uuid) {
        mPresenter.loadDepartments();
        refreshButtons();
    }

    private void refreshListData(List<DepartmentVM> departmentEntities){
        mPresenter.loadDepartments();
    }

}
