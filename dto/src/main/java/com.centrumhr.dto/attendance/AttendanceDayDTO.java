package com.centrumhr.dto.attendance;


import com.centrumhr.dto.common.Hour;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class AttendanceDayDTO {

    private UUID uniqueId;
    private UUID employeeId;
    private AttendanceType type;
    private Hour planStartHour;
    private Hour planEndHour;
    private Hour attendStartHour;
    private Hour attendEndHour;
    private String description;
    private Date date;

    public AttendanceDayDTO(UUID uniqueId,UUID employeeId ,Date date, AttendanceType type, Hour planStartHour, Hour planEndHour, Hour attendStartHour, Hour attendEndHour, String description ) {
        this.uniqueId = uniqueId;
        this.employeeId = employeeId;
        this.date = date;
        this.type = type;
        this.planStartHour = planStartHour;
        this.planEndHour = planEndHour;
        this.attendStartHour = attendStartHour;
        this.attendEndHour = attendEndHour;
        this.description = description;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public Date getDate() {
        return date;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public AttendanceType getType() {
        return type;
    }

    public Hour getPlanStartHour() {
        return planStartHour;
    }

    public Hour getPlanEndHour() {
        return planEndHour;
    }

    public Hour getAttendStartHour() {
        return attendStartHour;
    }

    public Hour getAttendEndHour() {
        return attendEndHour;
    }

    public String getDescription() {
        return description;
    }

}
