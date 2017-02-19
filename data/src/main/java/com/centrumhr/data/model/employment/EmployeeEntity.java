package com.centrumhr.data.model.employment;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.centrumhr.dto.employment.WorkTime;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
@DatabaseTable(tableName = "employee")
public class EmployeeEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField( dataType = DataType.UUID , unique = true)
    private UUID uniqueId;

    @DatabaseField private String firstName;
    @DatabaseField private String surname;
    @DatabaseField private String code;
    @DatabaseField private WorkTime workTime;

    @DatabaseField private boolean isJudgment;
    @DatabaseField private Date employmentDate;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private WorkFunctionEntity workFunctionEntity;

    @ForeignCollectionField(eager = true)
    private Collection<EmployeeDepartmentEntity> departments;

    public EmployeeEntity() {
        this.uniqueId = UUID.randomUUID();
    }

    public EmployeeEntity(String firstName, String surname, String code) {
        this.uniqueId = UUID.randomUUID();
        this.firstName = firstName;
        this.surname = surname;
        this.code = code;
    }

    public EmployeeEntity(UUID uniqueId, String firstName, String surname, String code, WorkTime workTime, boolean isJudgment, Date employmentDate, WorkFunctionEntity workFunctionEntity, Collection<EmployeeDepartmentEntity> departments) {
        this.uniqueId = uniqueId;
        this.firstName = firstName;
        this.surname = surname;
        this.code = code;
        this.workTime = workTime;
        this.isJudgment = isJudgment;
        this.employmentDate = employmentDate;
        this.workFunctionEntity = workFunctionEntity;
        this.departments = departments;
    }

    public void update(EmployeeEntity employeeEntity){
        this.firstName = employeeEntity.firstName;
        this.surname = employeeEntity.surname;
        this.code = employeeEntity.code;
        this.uniqueId = employeeEntity.uniqueId;
        this.workTime = employeeEntity.workTime;
        this.employmentDate = employeeEntity.employmentDate;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName(){
        return firstName + " " + surname;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getCode() {
        return code;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public boolean isJudgment() {
        return isJudgment;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public WorkFunctionEntity getWorkFunctionEntity() {
        return workFunctionEntity;
    }

    public Collection<EmployeeDepartmentEntity> getDepartments() {
        return departments;
    }

    void setJudgment(boolean judgment) {
        isJudgment = judgment;
    }

    void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setCode(String code) {
        this.code = code;
    }

    void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public void setWorkFunctionEntity(WorkFunctionEntity workFunctionEntity) {
        this.workFunctionEntity = workFunctionEntity;
    }

    public void setDepartments(Collection<EmployeeDepartmentEntity> departments) {
        this.departments = departments;
    }
}
