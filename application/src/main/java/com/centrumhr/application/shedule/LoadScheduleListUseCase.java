package com.centrumhr.application.shedule;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.data.mapper.attendance.AttendancePlanMapper;
import com.centrumhr.domain.attendance.AttendancePlan;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.attendance.AttendancePlanDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Seweryn on 19.02.2017.
 */
public class LoadScheduleListUseCase extends UseCase<Boolean,List<AttendancePlanDTO>> {

    private IAttendancePlanRepository attendancePlanRepository;

    @Inject
    public LoadScheduleListUseCase(IAttendancePlanRepository attendancePlanRepository) {
        this.attendancePlanRepository = attendancePlanRepository;
    }

    @Override
    public List<AttendancePlanDTO> execute(Boolean argument) throws DomainException {
        return attendancePlanRepository.list().stream()
                .map(AttendancePlan::convert)
                .collect(Collectors.toList());
    }
}
