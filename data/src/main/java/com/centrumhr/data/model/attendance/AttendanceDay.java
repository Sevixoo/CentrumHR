package com.centrumhr.data.model.attendance;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Seweryn on 15.10.2016.
 */
@DatabaseTable(tableName = "attendanceDay")
public class AttendanceDay {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField( dataType = DataType.UUID , unique = true)
    private UUID uniqueId;

    @DatabaseField
    private int day;

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
    private AttendanceEmployee employee;

    public AttendanceDay(){}

    public AttendanceDay(int day) {
        this.uniqueId = UUID.randomUUID();
        this.day = day;
        this.type = AttendanceType.EMPTY;
    }

    public void setPlanType(AttendanceType type,Hour planStartHour,Hour planEndHour) {
        this.type = type;
        this.planStartHour = planStartHour;
        this.planEndHour = planEndHour;
    }

    public void update( AttendanceDay attendanceDay ){
        this.uniqueId = attendanceDay.uniqueId;
        this.day = attendanceDay.day;
        this.type = attendanceDay.type;
        this.planStartHour = attendanceDay.planStartHour;
        this.planEndHour = attendanceDay.planEndHour;
        this.attendStartHour = attendanceDay.attendStartHour;
        this.attendEndHour = attendanceDay.attendEndHour;
        this.description = attendanceDay.description;
    }

    public void setEmployee(AttendanceEmployee employee) {
        this.employee = employee;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public int getDay() {
        return day;
    }

    public AttendanceType getType() {
        return type;
    }

    public Hour getPlanStartHour() {
        return (Hour)planStartHour;
    }

    public Hour getPlanEndHour() {
        return (Hour)planEndHour;
    }

    public Hour getAttendStartHour() {
        return (Hour)attendStartHour;
    }

    public Hour getAttendEndHour() {
        return (Hour)attendEndHour;
    }

    public String getDescription() {
        return description;
    }

    public AttendanceEmployee getEmployee() {
        return employee;
    }
}
