package com.centrumhr.data.model.attendance;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Seweryn on 15.10.2016.
 */
@DatabaseTable(tableName = "attendancePlan")
public class AttendancePlan {

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
    private Collection<AttendanceEmployee> employees;

    public AttendancePlan() {
        uniqueId = UUID.randomUUID();
    }

    public void update( AttendancePlan attendancePlan ){
        uniqueId = attendancePlan.uniqueId;
        name = attendancePlan.name;
        startDate = attendancePlan.startDate;
        createDate = attendancePlan.createDate;
        state = attendancePlan.state;
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

    void setEmployees( Collection<AttendanceEmployee> employees ){
        this.employees = employees;
    }

    public Collection<AttendanceEmployee> getEmployees() {
        return employees;
    }
}
