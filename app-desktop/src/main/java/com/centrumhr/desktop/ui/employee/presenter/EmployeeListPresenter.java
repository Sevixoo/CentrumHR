package com.centrumhr.desktop.ui.employee.presenter;

import com.centrumhr.application.Message;
import com.centrumhr.application.core.Presenter;
import com.centrumhr.application.core.UI;
import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.employee.LoadEmployeesUseCase;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import com.centrumhr.dto.employment.EmployeeDTO;
import rx.schedulers.JavaFxScheduler;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeListPresenter extends Presenter<EmployeeListPresenter.View>{

    public interface View extends UI{
        void displayAddedEmployee(EmployeeVM employee );
        void displayEmployeeList( List<EmployeeVM> employees );
    }

    private LoadEmployeesUseCase loadEmployeesUseCase;

    @Inject
    public EmployeeListPresenter(LoadEmployeesUseCase loadEmployeesUseCase) {
        super(JavaFxScheduler.getInstance(), UseCaseThreadExecutor.INSTANCE);
        this.loadEmployeesUseCase = loadEmployeesUseCase;
    }

    public void loadEmployees(){
        view.showProgress();
        executeUseCase(loadEmployeesUseCase,data -> {
                List<EmployeeVM> list = data.stream()
                        .map(EmployeeVM::new)
                        .collect(Collectors.toList());
                view.hideProgress();
                view.displayEmployeeList(list);
            },throwable -> {
                view.hideProgress();
                view.displayError(throwable);
            }
        );
    }

}
