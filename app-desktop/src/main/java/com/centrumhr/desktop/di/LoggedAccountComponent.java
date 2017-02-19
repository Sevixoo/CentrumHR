package com.centrumhr.desktop.di;

import com.centrumhr.desktop.ui.employee.presenter.AddEmployeePresenter;
import com.centrumhr.desktop.ui.employee.presenter.EmployeeListPresenter;
import com.centrumhr.desktop.ui.schedule.list.ScheduleListController;
import com.centrumhr.desktop.ui.settings.department.AddDepartmentController;
import com.centrumhr.desktop.ui.settings.department.DepartmentSettingsController;
import com.centrumhr.desktop.ui.settings.department.presenter.AddDepartmentPresenter;
import com.centrumhr.desktop.ui.settings.department.presenter.DepartmentPresenter;
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
    void inject(DepartmentSettingsController controller);
    void inject(AddDepartmentController controller);

    void inject(ScheduleListController scheduleListController);
}
