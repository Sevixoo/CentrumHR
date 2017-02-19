package com.centrumhr.desktop.ui.employee.data;

import com.centrumhr.data.model.employment.EmployeeDepartmentEntity;
import com.centrumhr.data.model.employment.EmployeeEntity;
import com.centrumhr.desktop.ui.settings.department.data.DepartmentVM;
import com.centrumhr.dto.employment.EmployeeDTO;
import com.centrumhr.dto.employment.WorkTime;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class EmployeeVM {

    private SimpleStringProperty name;
    private SimpleStringProperty code;
    private UUID uuid;

    private String firstName;
    private String surname;
    private WorkTime workTime;
    private Boolean judgment;
    private WorkFunctionVM workFunction;
    private List<DepartmentVM> departments;
    private Date employmentDate;

    public EmployeeVM(EmployeeDTO employeeDTO) {
        this.uuid = employeeDTO.getUniqueId();
        this.name = new SimpleStringProperty(employeeDTO.getFirstName() + " " + employeeDTO.getSurname());
        this.code = new SimpleStringProperty(employeeDTO.getCode());
        this.firstName = employeeDTO.getFirstName();
        this.surname = employeeDTO.getSurname();
        this.workTime = employeeDTO.getWorkTime();
        this.employmentDate =employeeDTO.getEmploymentDate();
        this.judgment = employeeDTO.isJudgment();
        this.workFunction = WorkFunctionVM.create(employeeDTO.getWorkFunction());
        if(employeeDTO.getDepartments()!=null){
            this.departments = employeeDTO.getDepartments().stream().map(DepartmentVM::new).collect(Collectors.toList());
        }else{
            this.departments = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeVM)) return false;
        EmployeeVM that = (EmployeeVM) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name.get();
    }

    public String getCode() {
        return code.get();
    }

    @Override
    public String toString() {
        return getCode() + " " + getName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public boolean isJudgment() {
        return judgment;
    }

    public WorkFunctionVM getWorkFunction() {
        return workFunction;
    }

    public List<DepartmentVM> getDepartments() {
        return departments;
    }
}
