package com.centrumhr.data.model.employment;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
@DatabaseTable(tableName = "employeeDepartment")
public class EmployeeDepartmentEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(foreign = true,foreignAutoCreate = true, foreignAutoRefresh = true)
    private EmployeeEntity employeeEntity;

    @DatabaseField(foreign = true,foreignAutoCreate = true, foreignAutoRefresh = true)
    private DepartmentEntity departmentEntity;

    public EmployeeDepartmentEntity(  ){}

    public EmployeeDepartmentEntity(EmployeeEntity employeeEntity, DepartmentEntity departmentEntity){
        this.employeeEntity = employeeEntity;
        this.departmentEntity = departmentEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public Long getId() { return id; }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }
}
