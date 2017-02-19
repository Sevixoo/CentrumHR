package com.centrumhr.desktop.ui.settings.department.presenter;

import com.centrumhr.application.core.Presenter;
import com.centrumhr.application.core.UI;
import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.department.DeleteDepartmentUseCase;
import com.centrumhr.application.department.LoadDepartmentsUseCase;
import com.centrumhr.desktop.ui.settings.department.data.DepartmentVM;
import com.centrumhr.dto.employment.DepartmentDTO;
import javafx.print.Collation;
import rx.schedulers.JavaFxScheduler;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class DepartmentPresenter extends Presenter<DepartmentPresenter.View>{

    public interface View extends UI{
        void displayDepartmentList(List<DepartmentVM> data);
        void onDepartmentDeleted(UUID uuid);
    }

    private LoadDepartmentsUseCase loadDepartmentsUseCase;
    private DeleteDepartmentUseCase deleteDepartmentUseCase;

    @Inject
    public DepartmentPresenter(LoadDepartmentsUseCase loadDepartmentsUseCase, DeleteDepartmentUseCase deleteDepartmentUseCase) {
        super(JavaFxScheduler.getInstance(), UseCaseThreadExecutor.INSTANCE);
        this.loadDepartmentsUseCase = loadDepartmentsUseCase;
        this.deleteDepartmentUseCase = deleteDepartmentUseCase;
    }

    public void loadDepartments(){
        executeUseCase(loadDepartmentsUseCase, departmentDTOs -> {
                    List<DepartmentVM> data = departmentDTOs.stream()
                            .map(DepartmentVM::new)
                            .collect(Collectors.toList());
                    view.displayDepartmentList(data);
                },
                view::displayError
        );
    }

    public void deleteDepartment( UUID uuid ){
        executeUseCase(deleteDepartmentUseCase,uuid,
                view::onDepartmentDeleted,
                view::displayError
        );
    }

}
