package com.centrumhr.desktop.di;

import com.centrumhr.application.application.employee.EmployeeUseCaseFactory;
import com.centrumhr.application.presenter.AddEmployeePresenter;
import com.centrumhr.application.presenter.EmployeeListPresenter;
import com.centrumhr.desktop.ui.employee.AddEmployeeController;
import com.centrumhr.desktop.ui.employee.EmployeeListController;
import dagger.Subcomponent;

/**
 * Created by Seweryn on 18.09.2016.
 */
@Subcomponent(modules = { AccountModule.class , DataBaseModule.class })
public interface LoggedAccountComponent {

    void inject(AddEmployeeController controller);
    void inject(EmployeeListController controller);

    AddEmployeePresenter getAddEmployeePresenter();
    EmployeeListPresenter getEmployeeListPresenter();
    EmployeeUseCaseFactory getEmployeeUseCaseFactory();

}
