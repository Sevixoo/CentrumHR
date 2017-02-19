package com.centrumhr.application.shedule;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.domain.attendance.AttendancePlan;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;

/**
 * Created by Seweryn on 19.02.2017.
 */
public class ChangeAttendanceNameUseCase extends UseCase<String,String> {

    private ScheduleCreatorProvider scheduleCreatorProvider;

    @Inject
    public ChangeAttendanceNameUseCase(ScheduleCreatorProvider scheduleCreatorProvider) {
        this.scheduleCreatorProvider = scheduleCreatorProvider;
    }

    @Override
    public String execute(String name) throws DomainException {
        AttendancePlan attendancePlan = scheduleCreatorProvider.get();
        attendancePlan.setName(name);
        return name;
    }
}
