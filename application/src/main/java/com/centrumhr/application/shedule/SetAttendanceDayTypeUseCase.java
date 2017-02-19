package com.centrumhr.application.shedule;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.application.shedule.data.SetAttendanceDayTypeArgument;
import com.centrumhr.application.shedule.data.SetAttendanceDayTypeResult;
import com.centrumhr.domain.attendance.AttendancePlan;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.domain.schedule.ScheduleService;
import com.centrumhr.dto.attendance.AttendanceDayDTO;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.10.2016.
 */
public class SetAttendanceDayTypeUseCase extends UseCase<SetAttendanceDayTypeArgument,SetAttendanceDayTypeResult> {

    private ScheduleService             scheduleService;
    private ScheduleCreatorProvider     scheduleProvider;

    @Inject
    public SetAttendanceDayTypeUseCase(ScheduleService scheduleService, ScheduleCreatorProvider scheduleProvider) {
        this.scheduleService = scheduleService;
        this.scheduleProvider = scheduleProvider;
    }

    @Override
    public SetAttendanceDayTypeResult execute(SetAttendanceDayTypeArgument argument) throws DomainException {
        AttendancePlan attendancePlan = scheduleProvider.get();
        SetAttendanceDayTypeResult result = new SetAttendanceDayTypeResult();
        result.employeeId = argument.employeeUniqueId;
        result.changedDay = scheduleService.scheduleDay(
                attendancePlan ,
                argument.employeeUniqueId,
                argument.day,
                argument.attendanceType,
                argument.hourFrom,
                argument.hourTo
        );
        result.employeeSummary = scheduleService.employeeSummary(
                attendancePlan,
                argument.employeeUniqueId
        );
        return result;
    }
}
