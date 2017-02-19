package com.centrumhr.data.model.attendance;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import com.centrumhr.dto.attendance.PlanState;
/**
 * Created by Seweryn on 15.10.2016.
 */
@DatabaseTable(tableName = "attendancePlan")
public class AttendancePlanEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField( dataType = DataType.UUID , unique = true)
    private UUID uniqueId;

    @DatabaseField
    private String name;

    @DatabaseField
    private Date startDate;

    @DatabaseField
    private Date createDate;

    @DatabaseField
    private PlanState state;

    @ForeignCollectionField(eager = true)
    private Collection<AttendanceEmployeeEntity> employees;

    public AttendancePlanEntity() {
        uniqueId = UUID.randomUUID();
    }

    public AttendancePlanEntity(UUID uniqueId, String name, Date startDate, Date createDate, PlanState state) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.startDate = startDate;
        this.createDate = createDate;
        this.state = state;
    }

    public void update(AttendancePlanEntity attendancePlanEntity){
        uniqueId = attendancePlanEntity.uniqueId;
        name = attendancePlanEntity.name;
        startDate = attendancePlanEntity.startDate;
        createDate = attendancePlanEntity.createDate;
        state = attendancePlanEntity.state;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees( Collection<AttendanceEmployeeEntity> employees ){
        this.employees = employees;
    }

    public Collection<AttendanceEmployeeEntity> getEmployees() {
        return employees;
    }
}
