package com.centrumhr.desktop.service.attendance;

import com.centrumhr.domain.attendance.AttendanceDay;
import com.centrumhr.domain.attendance.IAttendanceHistoryService;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Seweryn on 28.05.2017.
 */
public class HistoryService implements IAttendanceHistoryService {
    @Override
    public AttendanceDay getAttendanceDay(UUID employeeUniqueId, Date date) {
        return null;
    }
}
