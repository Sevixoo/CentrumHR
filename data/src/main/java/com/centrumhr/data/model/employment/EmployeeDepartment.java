package com.centrumhr.data.model.employment;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
@DatabaseTable(tableName = "employeeDepartment")
public class EmployeeDepartment {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField( dataType = DataType.UUID , unique = true)
    private UUID uniqueId;

    @DatabaseField(foreign = true)
    private Employee employee;

    @DatabaseField(foreign = true)
    private Department department;

    public EmployeeDepartment(  ){}

    public EmployeeDepartment( Employee employee , Department department ){
        uniqueId = UUID.randomUUID();
        this.employee = employee;
        this.department = department;
    }

    public void update(EmployeeDepartment employeeDepartment){
        this.uniqueId = employeeDepartment.getUniqueId();
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Department getDepartment() {
        return department;
    }
}
