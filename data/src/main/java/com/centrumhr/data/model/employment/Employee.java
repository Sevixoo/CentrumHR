package com.centrumhr.data.model.employment;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
@DatabaseTable(tableName = "employee")
public class Employee {

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

    @DatabaseField(foreign = true)
    private WorkFunction workFunction;

    @ForeignCollectionField(eager = true)
    private Collection<EmployeeDepartment> departments;

    public Employee() {
        this.uniqueId = UUID.randomUUID();
    }

    public Employee(String firstName, String surname, String code) {
        this.uniqueId = UUID.randomUUID();
        this.firstName = firstName;
        this.surname = surname;
        this.code = code;
    }

    public void update( Employee employee ){
        this.firstName = employee.firstName;
        this.surname = employee.surname;
        this.code = employee.code;
        this.uniqueId = employee.uniqueId;
        this.workTime = employee.workTime;
        this.employmentDate = employee.employmentDate;
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

    public WorkFunction getWorkFunction() {
        return workFunction;
    }

    public Collection<EmployeeDepartment> getDepartments() {
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

    public void setWorkFunction(WorkFunction workFunction) {
        this.workFunction = workFunction;
    }

    public void setDepartments(Collection<EmployeeDepartment> departments) {
        this.departments = departments;
    }
}
