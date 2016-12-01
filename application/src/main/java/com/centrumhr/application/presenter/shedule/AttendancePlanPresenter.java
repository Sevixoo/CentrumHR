package com.centrumhr.application.presenter.shedule;

import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.shedule.ScheduleUseCaseFactory;
import com.centrumhr.data.model.attendance.AttendancePlan;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 13.11.2016.
 */
public class AttendancePlanPresenter {

    public interface View{
        void displayError( String message );
        void displayAttendancePlan( AttendancePlan attendancePlan);
    }

    private View mView;
    private UseCase mPendingUseCase;
    private ScheduleUseCaseFactory mScheduleUseCaseFactory;

    @Inject
    public AttendancePlanPresenter(ScheduleUseCaseFactory mScheduleUseCaseFactory) {
        this.mScheduleUseCaseFactory = mScheduleUseCaseFactory;
    }

    public void setView(View view ){
        mView = view;
    }

    public void loadAttendancePlan(UUID uuid){
        mPendingUseCase = mScheduleUseCaseFactory.createLoadAttendancePlanUseCase(uuid).execute(new UseCaseCallback<AttendancePlan>() {
            @Override
            public void onResult(AttendancePlan data) {
                mView.displayAttendancePlan(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }

    public void savePlan( AttendancePlan data ){

    }


}
