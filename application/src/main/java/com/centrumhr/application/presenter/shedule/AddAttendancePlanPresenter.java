package com.centrumhr.application.presenter.shedule;

import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.employee.EmployeeUseCaseFactory;
import com.centrumhr.application.application.shedule.ScheduleUseCaseFactory;
import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.employment.Employee;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 05.11.2016.
 */
public class AddAttendancePlanPresenter {

    public interface View{
        void displayEmployeesList( List<Employee> employees );
        void onAttendancePlanCreated( AttendancePlan attendancePlan );
        void displayError( String message );
    }

    private View mView;
    private ScheduleUseCaseFactory mScheduleUseCaseFactory;
    private EmployeeUseCaseFactory mEmployeeUseCaseFactory;
    private UseCase mPendingUseCase;

    @Inject
    public AddAttendancePlanPresenter(ScheduleUseCaseFactory mScheduleUseCaseFactory,EmployeeUseCaseFactory employeeUseCaseFactory) {
        this.mScheduleUseCaseFactory = mScheduleUseCaseFactory;
        this.mEmployeeUseCaseFactory = employeeUseCaseFactory;
    }

    public void setView(View mView) {
        this.mView = mView;
    }

    public void loadEmployeesList(){
        mPendingUseCase = mEmployeeUseCaseFactory.createLoadEmployeesUseCase().execute(new UseCaseCallback<List<Employee>>() {
            @Override
            public void onResult(List<Employee> data) {
                mView.displayEmployeesList(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }

    public void createAttendancePlan(String name , Date beginDate , List<UUID> employeeUniqueIdList ){
        mPendingUseCase = mScheduleUseCaseFactory.createCreateAttendancePlanUseCase(name , beginDate , employeeUniqueIdList).execute(new UseCaseCallback<AttendancePlan>() {
            @Override
            public void onResult(AttendancePlan data) {
                mView.onAttendancePlanCreated(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }

}
