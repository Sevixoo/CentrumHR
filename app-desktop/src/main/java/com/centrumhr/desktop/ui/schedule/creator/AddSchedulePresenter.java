package com.centrumhr.desktop.ui.schedule.creator;

import com.centrumhr.application.core.Presenter;
import com.centrumhr.application.core.UI;
import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.employee.LoadEmployeesUseCase;
import com.centrumhr.application.shedule.CreateAttendancePlanUseCase;
import com.centrumhr.application.shedule.data.CreateAttendancePlanArgument;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import com.centrumhr.desktop.ui.schedule.data.AttendancePlanVM;
import com.centrumhr.domain.attendance.ICalendarService;
import rx.schedulers.JavaFxScheduler;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Seweryn on 05.11.2016.
 */
public class AddSchedulePresenter extends Presenter<AddSchedulePresenter.View>{

    public interface View extends UI{
        void displayEmployeesList( List<EmployeeVM> employees );
        void onAttendancePlanCreated( AttendancePlanVM attendancePlan );
    }

    private LoadEmployeesUseCase        loadEmployeesUseCase;
    private CreateAttendancePlanUseCase createAttendancePlanUseCase;
    private ICalendarService            calendarService;

    @Inject
    public AddSchedulePresenter(ICalendarService calendarService, LoadEmployeesUseCase loadEmployeesUseCase, CreateAttendancePlanUseCase createAttendancePlanUseCase) {
        super(JavaFxScheduler.getInstance(), UseCaseThreadExecutor.INSTANCE);
        this.loadEmployeesUseCase = loadEmployeesUseCase;
        this.calendarService = calendarService;
        this.createAttendancePlanUseCase = createAttendancePlanUseCase;
    }

    public void loadEmployeesList(){
        executeUseCase(loadEmployeesUseCase, employeeDTOs -> {
                    List<EmployeeVM> list = employeeDTOs.stream().map(EmployeeVM::new).collect(Collectors.toList());
                    view.displayEmployeesList(list);
                },
                view::displayError
        );
    }

    public void createAttendancePlan(String name , Date beginDate , List<UUID> employeeUniqueIdList ){
        CreateAttendancePlanArgument argument = new CreateAttendancePlanArgument();
        argument.name = name;
        argument.month = beginDate;
        argument.employees = employeeUniqueIdList;

        executeUseCase(createAttendancePlanUseCase, argument, result -> {
                    view.onAttendancePlanCreated( new AttendancePlanVM(result.planDTO ,calendarService, result.summary) );
                },
                view::displayError
        );
    }

}
