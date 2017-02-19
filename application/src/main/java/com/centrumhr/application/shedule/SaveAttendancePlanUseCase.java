package com.centrumhr.application.shedule;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.domain.attendance.AttendancePlan;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;

/**
 * Created by Seweryn on 19.02.2017.
 */
public class SaveAttendancePlanUseCase extends UseCase<Boolean,Boolean> {

    private IAttendancePlanRepository attendancePlanRepository;
    private ScheduleCreatorProvider scheduleCreatorProvider;

    @Inject
    public SaveAttendancePlanUseCase(IAttendancePlanRepository attendancePlanRepository, ScheduleCreatorProvider scheduleCreatorProvider) {
        this.attendancePlanRepository = attendancePlanRepository;
        this.scheduleCreatorProvider = scheduleCreatorProvider;
    }

    @Override
    public Boolean execute(Boolean aBoolean) throws DomainException {
        AttendancePlan attendancePlan = scheduleCreatorProvider.get();
        attendancePlanRepository.save(attendancePlan);
        return true;
    }
}
