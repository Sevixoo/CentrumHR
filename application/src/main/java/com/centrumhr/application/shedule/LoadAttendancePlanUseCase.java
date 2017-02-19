package com.centrumhr.application.shedule;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.application.shedule.data.LoadAttendancePlanResult;
import com.centrumhr.domain.attendance.AttendancePlan;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.domain.schedule.ScheduleService;
import com.centrumhr.dto.attendance.AttendancePlanDTO;

import javax.inject.Inject;
import java.util.UUID;

public class LoadAttendancePlanUseCase extends UseCase<UUID,LoadAttendancePlanResult> {

    private ScheduleCreatorProvider     scheduleProvider;
    private IAttendancePlanRepository   attendancePlanRepository;

    @Inject
    public LoadAttendancePlanUseCase(IAttendancePlanRepository attendancePlanRepository, ScheduleCreatorProvider scheduleProvider) {
        this.attendancePlanRepository = attendancePlanRepository;
        this.scheduleProvider = scheduleProvider;
    }

    @Override
    public LoadAttendancePlanResult execute(UUID uuid) throws DomainException {
        AttendancePlan attendancePlan = attendancePlanRepository.find(uuid);
        scheduleProvider.set(attendancePlan);

        LoadAttendancePlanResult result = new LoadAttendancePlanResult();
        result.planDTO = attendancePlan.convert();
        result.summary = attendancePlan.employeeSummary();
        return result;
    }
}
