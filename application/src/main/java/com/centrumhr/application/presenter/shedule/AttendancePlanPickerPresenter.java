package com.centrumhr.application.presenter.shedule;

import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.shedule.ScheduleUseCaseFactory;
import com.centrumhr.application.application.shedule.usecase.SetAttendanceDayTypeUseCase;
import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.attendance.AttendanceType;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Seweryn on 18.10.2016.
 */
public class AttendancePlanPickerPresenter {

    public interface View{
        void onUpdateAttendanceDay( AttendanceDay attendanceDay );
        void displayError( String message );
    }

    private View mView;

    private ScheduleUseCaseFactory mScheduleUseCaseFactory;
    private UseCase mPendingUseCase;

    @Inject
    public AttendancePlanPickerPresenter(ScheduleUseCaseFactory mScheduleUseCaseFactory) {
        this.mScheduleUseCaseFactory = mScheduleUseCaseFactory;
    }

    public void setView(View mView) {
        this.mView = mView;
    }

    public void setAttendanceDayType(AttendancePlan attendancePlan , UUID employeeUniqueId, UUID  attendanceDayUniqueId , AttendanceType attendanceType ){
        mPendingUseCase = mScheduleUseCaseFactory.createSetAttendanceDayTypeUseCase(attendancePlan , employeeUniqueId ,
                attendanceDayUniqueId , attendanceType).execute(new UseCaseCallback<AttendanceDay>() {
            @Override
            public void onResult(AttendanceDay data) {
                mView.onUpdateAttendanceDay(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }


}
