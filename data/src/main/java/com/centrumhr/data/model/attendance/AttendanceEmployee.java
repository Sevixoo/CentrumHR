package com.centrumhr.data.model.attendance;

import com.centrumhr.data.model.employment.Employee;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by Seweryn on 15.10.2016.
 */
@DatabaseTable(tableName = "attendanceEmployee")
public class AttendanceEmployee {


    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField( dataType = DataType.UUID , unique = true)
    private UUID uniqueId;

    @DatabaseField
    private String name;

    @DatabaseField
    private String code;

    @DatabaseField
    private UUID employeeUniqueId;

    @ForeignCollectionField(eager = true)
    private Collection<AttendanceDay> attendanceDays;

    @DatabaseField(foreign = true)
    private AttendancePlan attendancePlan;

    public AttendanceEmployee() { }

    public AttendanceEmployee(Employee employee) {
        this.uniqueId = UUID.randomUUID();
        this.code = employee.getCode();
        this.name = employee.getName();
    }

    public void update( AttendanceEmployee attendanceEmployee ){
        this.uniqueId = attendanceEmployee.getUniqueId();
        this.code = attendanceEmployee.getCode();
        this.name = attendanceEmployee.getName();
    }

    public void setAttendancePlan(AttendancePlan attendancePlan) {
        this.attendancePlan = attendancePlan;
    }

    public Collection<AttendanceDay> getAttendanceDays() {
        return attendanceDays;
    }

    public void setAttendanceDays(Collection<AttendanceDay> attendanceDays) {
        this.attendanceDays = attendanceDays;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public UUID getEmployeeUniqueId() {
        return employeeUniqueId;
    }

    public AttendancePlan getAttendancePlan() {
        return attendancePlan;
    }
}
