package com.centrumhr.application.shedule.data;

import com.centrumhr.domain.schedule.AttendanceEmployeeSummary;
import com.centrumhr.dto.attendance.AttendancePlanDTO;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Seweryn on 19.02.2017.
 */
public class LoadAttendancePlanResult {

    public AttendancePlanDTO planDTO;
    public HashMap<UUID,AttendanceEmployeeSummary> summary;

}
