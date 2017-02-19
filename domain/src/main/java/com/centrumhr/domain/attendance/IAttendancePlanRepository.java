package com.centrumhr.domain.attendance;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 07.02.2017.
 */
public interface IAttendancePlanRepository {

    AttendancePlan find(UUID uniqueId);

    void save( AttendancePlan attendancePlan );

    List<AttendancePlan> list();
}
