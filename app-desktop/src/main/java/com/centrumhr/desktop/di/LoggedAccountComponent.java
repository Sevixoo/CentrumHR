package com.centrumhr.desktop.di;

import com.centrumhr.application.application.employee.EmployeeUseCaseFactory;
import com.centrumhr.application.presenter.AddEmployeePresenter;
import com.centrumhr.application.presenter.EmployeeListPresenter;
import com.centrumhr.application.presenter.department.AddDepartmentPresenter;
import com.centrumhr.application.presenter.department.DepartmentPresenter;
import com.centrumhr.application.presenter.shedule.AttendancePlanPickerPresenter;
import com.centrumhr.desktop.ui.employee.AddEmployeeController;
import com.centrumhr.desktop.ui.employee.EmployeeController;
import com.centrumhr.desktop.ui.employee.EmployeeListController;
import com.centrumhr.desktop.ui.schedule.creator.AddScheduleController;
import com.centrumhr.desktop.ui.schedule.planner.SchedulePlannerController;
import dagger.Subcomponent;

/**
 * Created by Seweryn on 18.09.2016.
 */
@Subcomponent(modules = { AccountModule.class , DataBaseModule.class , EmploymentModule.class })
public interface LoggedAccountComponent {

    void inject(AddEmployeeController controller);
    void inject(EmployeeListController controller);
    void inject(AddScheduleController controller);
    void inject(EmployeeController controller);
    void inject(SchedulePlannerController controller);

    AddEmployeePresenter getAddEmployeePresenter();
    EmployeeListPresenter getEmployeeListPresenter();
    EmployeeUseCaseFactory getEmployeeUseCaseFactory();
    AttendancePlanPickerPresenter getAttendancePlanPickerPresenter();
    DepartmentPresenter getDepartmentPresenter();
    AddDepartmentPresenter getAddDepartmentPresenter();
}
