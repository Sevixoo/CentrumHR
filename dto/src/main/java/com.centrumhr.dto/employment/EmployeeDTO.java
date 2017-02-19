package com.centrumhr.dto.employment;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class EmployeeDTO {

    private UUID uniqueId;
    private String firstName;
    private String surname;
    private String code;

    private WorkTime workTime;
    private boolean isJudgment;
    private Date employmentDate;

    private WorkFunctionDTO workFunction;
    private Collection<DepartmentDTO> departments;

    public EmployeeDTO(UUID uniqueId, String firstName, String surname, String code, WorkTime workTime, boolean isJudgment, Date employmentDate, WorkFunctionDTO workFunction, Collection<DepartmentDTO> departments) {
        this.uniqueId = uniqueId;
        this.firstName = firstName;
        this.surname = surname;
        this.code = code;
        this.workTime = workTime;
        this.isJudgment = isJudgment;
        this.employmentDate = employmentDate;
        this.workFunction = workFunction;
        this.departments = departments;
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

    public WorkFunctionDTO getWorkFunction() {
        return workFunction;
    }

    public Collection<DepartmentDTO> getDepartments() {
        return departments;
    }
}
