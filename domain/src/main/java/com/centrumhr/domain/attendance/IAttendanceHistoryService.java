package com.centrumhr.domain.attendance;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Seweryn on 28.05.2017.
 */
public interface IAttendanceHistoryService {

    AttendanceDay getAttendanceDay(UUID employeeUniqueId, Date date);

}
