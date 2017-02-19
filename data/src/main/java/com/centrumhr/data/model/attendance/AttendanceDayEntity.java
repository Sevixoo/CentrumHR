package com.centrumhr.data.model.attendance;

import com.centrumhr.dto.common.Hour;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

import com.centrumhr.dto.attendance.AttendanceType;
/**
 * Created by Seweryn on 15.10.2016.
 */
@DatabaseTable(tableName = "attendanceDay")
public class AttendanceDayEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField( dataType = DataType.UUID , unique = true)
    private UUID uniqueId;

    @DatabaseField
    private Date date;

    @DatabaseField
    private AttendanceType type;

    @DatabaseField
    private Date planStartHour;

    @DatabaseField
    private Date planEndHour;

    @DatabaseField
    private Date attendStartHour;

    @DatabaseField
    private Date attendEndHour;

    @DatabaseField
    private String description;

    @DatabaseField(foreign = true)
    private AttendanceEmployeeEntity employee;
    private boolean freeDay;

    public AttendanceDayEntity(){}

    public AttendanceDayEntity(UUID uniqueId, Date date, AttendanceType type, Date planStartHour, Date planEndHour, Date attendStartHour, Date attendEndHour, String description ) {
        this.uniqueId = uniqueId;
        this.date = date;
        this.type = type;
        this.planStartHour = planStartHour;
        this.planEndHour = planEndHour;
        this.attendStartHour = attendStartHour;
        this.attendEndHour = attendEndHour;
        this.description = description;
    }

    public void update( AttendanceDayEntity attendanceDayEntity){
        this.uniqueId = attendanceDayEntity.uniqueId;
        this.date = attendanceDayEntity.date;
        this.type = attendanceDayEntity.type;
        this.planStartHour = attendanceDayEntity.planStartHour;
        this.planEndHour = attendanceDayEntity.planEndHour;
        this.attendStartHour = attendanceDayEntity.attendStartHour;
        this.attendEndHour = attendanceDayEntity.attendEndHour;
        this.description = attendanceDayEntity.description;
    }

    public void setEmployee(AttendanceEmployeeEntity employee) {
        this.employee = employee;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public Date getDate() {
        return date;
    }

    public AttendanceType getType() {
        return type;
    }

    public Hour getPlanStartHour() {
        return Hour.create(planStartHour);
    }

    public Hour getPlanEndHour() {
        return Hour.create(planEndHour);
    }

    public Hour getAttendStartHour() {
        return Hour.create(attendStartHour);
    }

    public Hour getAttendEndHour() {
        return Hour.create(attendEndHour);
    }

    public String getDescription() {
        return description;
    }

    public AttendanceEmployeeEntity getEmployee() {
        return employee;
    }

    public boolean isFreeDay() {
        return freeDay;
    }
}
