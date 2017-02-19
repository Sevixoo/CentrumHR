package com.centrumhr.dto.attendance;


import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class AttendancePlanDTO {

    private UUID uniqueId;
    private String name;
    private Date startDate;
    private Date createDate;
    private PlanState state;

    private AttendanceEmployeeDTO[] employees;
    private AttendanceDayDTO[][] days;

    public AttendancePlanDTO(UUID uniqueId, String name, Date startDate, Date createDate, PlanState state, AttendanceEmployeeDTO[] employees, AttendanceDayDTO[][] days) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.startDate = startDate;
        this.createDate = createDate;
        this.state = state;
        this.employees = employees;
        this.days = days;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public PlanState getState() {
        return state;
    }

    public AttendanceEmployeeDTO[] getEmployees() {
        return employees;
    }

    public AttendanceDayDTO[][] getDays() {
        return days;
    }
}
