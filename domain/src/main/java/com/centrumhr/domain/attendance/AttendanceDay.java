package com.centrumhr.domain.attendance;

import com.centrumhr.dto.common.Hour;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.attendance.AttendanceDayDTO;
import com.centrumhr.dto.attendance.AttendanceType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 07.02.2017.
 */
public class AttendanceDay {

    private UUID            uniqueId;
    private UUID            employeeId;
    private AttendanceType  attendanceType;
    private Hour            planStartHour;
    private Hour            planEndHour;
    private Hour            attendStartHour;
    private Hour            attendEndHour;
    private Date            date;
    private String          description;
    private List<String>    errors;

    public AttendanceDay(Date date, UUID employeeId){
        if(date==null)throw new IllegalArgumentException("Date cant be null");
        this.uniqueId = UUID.randomUUID();
        this.employeeId = employeeId;
        this.attendanceType = AttendanceType.NONE;
        this.date = date;
        this.errors = new ArrayList<>();
    }

    public AttendanceDay(UUID uniqueId, UUID employeeId, AttendanceType attendanceType, Hour planStartHour, Hour planEndHour, Hour attendStartHour, Hour attendEndHour, Date date) {
        this.uniqueId = uniqueId;
        this.attendanceType = attendanceType;
        this.planStartHour = planStartHour;
        this.planEndHour = planEndHour;
        this.attendStartHour = attendStartHour;
        this.attendEndHour = attendEndHour;
        this.date = date;
        this.employeeId = employeeId;
        this.errors = new ArrayList<>();
    }

    public AttendanceDayDTO convert() {
        return new AttendanceDayDTO(
                uniqueId , employeeId , date , attendanceType , planStartHour ,
                planEndHour , attendStartHour , attendEndHour , description
        );
    }

    public boolean workScheduled() {
        return attendanceType != AttendanceType.NONE;
    }

    public int workDuration() {
        return Hour.duration(planStartHour, planEndHour);
    }

    public Date getDate() {
        return date;
    }

    void schedule(AttendanceType attendanceType, Hour hourFrom, Hour hourTo) {
        this.attendanceType = attendanceType;
        this.planStartHour = hourFrom;
        this.planEndHour = hourTo;
    }

    void addError(String errorMessage) {
        errors.add(errorMessage);
    }

    void clearErrors() {
        errors.clear();
    }

    public List<String> getErrors(){
        return errors;
    }

    public int planedDuration() {
        if(planStartHour==null || planEndHour == null)return 0;
        return Hour.durationHours(planStartHour,planEndHour);
    }
}
