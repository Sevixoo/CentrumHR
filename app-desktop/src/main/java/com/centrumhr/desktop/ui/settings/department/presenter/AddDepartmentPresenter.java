package com.centrumhr.desktop.ui.settings.department.presenter;

import com.centrumhr.application.core.Presenter;
import com.centrumhr.application.core.UI;
import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.department.AddDepartmentUseCase;
import com.centrumhr.desktop.ui.settings.department.data.DepartmentVM;
import com.centrumhr.dto.employment.DepartmentDTO;
import rx.schedulers.JavaFxScheduler;

import javax.inject.Inject;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class AddDepartmentPresenter extends Presenter<AddDepartmentPresenter.View>{

    public interface View extends UI{
        void onAddDepartmentSuccess(DepartmentVM departmentDTO);
        void displayError(String message);
    }

    private AddDepartmentUseCase addDepartmentUseCase;

    @Inject
    public AddDepartmentPresenter(AddDepartmentUseCase addDepartmentUseCase) {
        super(JavaFxScheduler.getInstance(), UseCaseThreadExecutor.INSTANCE);
        this.addDepartmentUseCase = addDepartmentUseCase;
    }

    public void addDepartment( String name ){
        executeUseCase(addDepartmentUseCase,name, departmentDTO -> {
                    view.onAddDepartmentSuccess( new DepartmentVM(departmentDTO) );
                },
                view::displayError
        );
    }

}
