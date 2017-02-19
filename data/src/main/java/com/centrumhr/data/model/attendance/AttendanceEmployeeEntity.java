package com.centrumhr.data.model.attendance;

import com.centrumhr.data.model.employment.EmployeeEntity;
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
public class AttendanceEmployeeEntity {


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
    private Collection<AttendanceDayEntity> attendanceDayEntities;

    @DatabaseField(foreign = true)
    private AttendancePlanEntity attendancePlanEntity;

    public AttendanceEmployeeEntity() { }

    public AttendanceEmployeeEntity(UUID uniqueId, String name, String code, UUID employeeUniqueId) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.code = code;
        this.employeeUniqueId = employeeUniqueId;
    }

    public AttendanceEmployeeEntity(EmployeeEntity employeeEntity) {
        this.uniqueId = UUID.randomUUID();
        this.code = employeeEntity.getCode();
        this.name = employeeEntity.getName();
    }

    public void update( AttendanceEmployeeEntity attendanceEmployeeEntity){
        this.uniqueId = attendanceEmployeeEntity.getUniqueId();
        this.code = attendanceEmployeeEntity.getCode();
        this.name = attendanceEmployeeEntity.getName();
    }

    public void setAttendancePlanEntity(AttendancePlanEntity attendancePlanEntity) {
        this.attendancePlanEntity = attendancePlanEntity;
    }

    public Collection<AttendanceDayEntity> getAttendanceDayEntities() {
        return attendanceDayEntities;
    }

    public void setAttendanceDayEntities(Collection<AttendanceDayEntity> attendanceDayEntities) {
        this.attendanceDayEntities = attendanceDayEntities;
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

    public AttendancePlanEntity getAttendancePlanEntity() {
        return attendancePlanEntity;
    }
}
