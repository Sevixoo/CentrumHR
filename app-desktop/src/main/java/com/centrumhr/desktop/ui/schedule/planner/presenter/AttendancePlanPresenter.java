package com.centrumhr.desktop.ui.schedule.planner.presenter;

import com.centrumhr.application.core.Presenter;
import com.centrumhr.application.core.UI;
import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.shedule.ChangeAttendanceNameUseCase;
import com.centrumhr.application.shedule.LoadAttendancePlanUseCase;
import com.centrumhr.application.shedule.SaveAttendancePlanUseCase;
import com.centrumhr.application.shedule.SetAttendanceDayTypeUseCase;
import com.centrumhr.application.shedule.data.SetAttendanceDayTypeArgument;
import com.centrumhr.desktop.ui.schedule.data.AttendanceDayVM;
import com.centrumhr.desktop.ui.schedule.data.AttendancePlanVM;
import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.schedule.AttendanceEmployeeSummary;
import com.centrumhr.dto.attendance.AttendancePlanDTO;
import com.centrumhr.dto.attendance.AttendanceType;
import com.centrumhr.dto.common.Hour;
import rx.schedulers.JavaFxScheduler;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Seweryn on 13.11.2016.
 */
public class AttendancePlanPresenter extends Presenter<AttendancePlanPresenter.View>{

    public interface View extends UI{
        void displayAttendancePlan( AttendancePlanVM attendancePlan );
        void onUpdateAttendanceDay( AttendanceDayVM attendanceDay );
        void onUpdateEmployeeSummary(UUID employeeId, AttendanceEmployeeSummary employeeSummary);
    }

    private SetAttendanceDayTypeUseCase setAttendanceDayTypeUseCase;
    private LoadAttendancePlanUseCase loadAttendancePlanUseCase;
    private ChangeAttendanceNameUseCase changeAttendanceNameUseCase;
    private SaveAttendancePlanUseCase saveAttendancePlanUseCase;
    private ICalendarService calendarService;

    @Inject
    public AttendancePlanPresenter(ICalendarService calendarService, LoadAttendancePlanUseCase loadAttendancePlanUseCase,SaveAttendancePlanUseCase saveAttendancePlanUseCase,SetAttendanceDayTypeUseCase setAttendanceDayTypeUseCase,ChangeAttendanceNameUseCase changeAttendanceNameUseCase) {
        super(JavaFxScheduler.getInstance(), UseCaseThreadExecutor.INSTANCE);
        this.loadAttendancePlanUseCase = loadAttendancePlanUseCase;
        this.setAttendanceDayTypeUseCase = setAttendanceDayTypeUseCase;
        this.changeAttendanceNameUseCase = changeAttendanceNameUseCase;
        this.saveAttendancePlanUseCase = saveAttendancePlanUseCase;
        this.calendarService = calendarService;
    }

    public void loadAttendancePlan(UUID uuid){
        executeUseCase(loadAttendancePlanUseCase,uuid, result -> {
                        view.displayAttendancePlan(new AttendancePlanVM(result.planDTO,calendarService,result.summary));
                },
                view::displayError
        );
    }

    public void setAttendanceDayType( AttendanceDayVM attendanceDayVM ,AttendanceType attendanceType , Hour hourFrom , Hour hourTo ){
        SetAttendanceDayTypeArgument argument = new SetAttendanceDayTypeArgument();
        argument.attendanceType =attendanceType;
        argument.employeeUniqueId = attendanceDayVM.employeeUniqueId;
        argument.day = attendanceDayVM.day;
        argument.hourFrom = hourFrom;
        argument.hourTo = hourTo;

        executeUseCase(setAttendanceDayTypeUseCase,argument, result -> {
                    view.onUpdateAttendanceDay( new AttendanceDayVM(result.changedDay,attendanceDayVM.day,calendarService) );
                    view.onUpdateEmployeeSummary( result.employeeId , result.employeeSummary );
                },
                view::displayError
        );
    }

    public void changeAttendancePlanName(String name) {
        executeUseCase(changeAttendanceNameUseCase,name,
                s -> {},
                view::displayError
        );
    }

    public void saveAttendancePlan() {
        view.showProgress();
        executeUseCase(saveAttendancePlanUseCase,aBoolean -> {
            view.hideProgress();
        },throwable -> {
            view.hideProgress();
            view.displayError(throwable);
        });
    }

}
