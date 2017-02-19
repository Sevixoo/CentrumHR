package com.centrumhr.application.shedule;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.application.shedule.data.CreateAttendancePlanArgument;
import com.centrumhr.application.shedule.data.LoadAttendancePlanResult;
import com.centrumhr.data.mapper.employment.EmployeeMapper;
import com.centrumhr.data.model.employment.IEmployeeRepository;
import com.centrumhr.domain.attendance.AttendancePlan;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.domain.schedule.ScheduleService;
import com.centrumhr.dto.attendance.AttendancePlanDTO;

import javax.inject.Inject;

/**
 * Created by Seweryn on 05.11.2016.
 */
public class CreateAttendancePlanUseCase extends UseCase<CreateAttendancePlanArgument,LoadAttendancePlanResult> {

    private ScheduleService             scheduleService;
    private ScheduleCreatorProvider     scheduleProvider;

    private IEmployeeRepository         employeeRepository;
    private IAttendancePlanRepository   attendancePlanRepository;

    @Inject
    public CreateAttendancePlanUseCase(IAttendancePlanRepository attendancePlanRepository,ScheduleService scheduleService, ScheduleCreatorProvider scheduleProvider, IEmployeeRepository employeeRepository) {
        this.scheduleService = scheduleService;
        this.scheduleProvider = scheduleProvider;
        this.employeeRepository = employeeRepository;
        this.attendancePlanRepository = attendancePlanRepository;
    }

    @Override
    public LoadAttendancePlanResult execute(CreateAttendancePlanArgument createAttendancePlanArgument) throws DomainException {
        AttendancePlan attendancePlan = scheduleService.createNewPlan(
                createAttendancePlanArgument.name,
                EmployeeMapper.INSTANCE.backward(
                        employeeRepository.list(createAttendancePlanArgument.employees)
                ),
                createAttendancePlanArgument.month
        );
        attendancePlanRepository.save(attendancePlan);
        scheduleProvider.set(attendancePlan);

        LoadAttendancePlanResult result = new LoadAttendancePlanResult();
        result.planDTO = attendancePlan.convert();
        result.summary = attendancePlan.employeeSummary();
        return result;
    }


}
