package com.centrumhr.application.application.shedule.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IAttendancePlanRepository;
import com.centrumhr.data.model.attendance.AttendancePlan;

import java.util.UUID;

/**
 * Created by Seweryn on 13.11.2016.
 */
public class LoadAttendancePlanUseCase extends UseCase<AttendancePlan> {

    private UUID uniqueId;
    private IAttendancePlanRepository mAttendancePlanRepository;

    public LoadAttendancePlanUseCase(IExecutor executor, IHandler handler, IAttendancePlanRepository mAttendancePlanRepository, UUID uniqueId) {
        super(executor, handler);
        this.uniqueId = uniqueId;
        this.mAttendancePlanRepository = mAttendancePlanRepository;
    }

    @Override
    public AttendancePlan execute() throws Exception {
        return mAttendancePlanRepository.load(uniqueId);
    }
}
