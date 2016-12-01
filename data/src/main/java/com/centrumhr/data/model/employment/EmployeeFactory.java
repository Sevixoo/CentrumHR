package com.centrumhr.data.model.employment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Seweryn on 25.10.2016.
 */
public class EmployeeFactory {

    public EmployeeFactory() { }

    public Employee createEmployee( Employee employee, String firstName, String lastName, String code, WorkFunction workFunction , List<Department> departmentList, Date employmentDate , boolean isJudgment , WorkTime workTime ){
        employee.setFirstName(firstName);
        employee.setSurname(lastName);
        employee.setCode(code);
        employee.setEmploymentDate(employmentDate);
        employee.setWorkTime(workTime);
        employee.setJudgment(isJudgment);
        employee.setWorkFunction( workFunction );
        employee.setDepartments( createEmployeeDepartments(employee , departmentList) );
        return employee;
    }

    public Employee createEmployee(String firstName, String lastName, String code, WorkFunction workFunction , List<Department> departmentList, Date employmentDate , boolean isJudgment , WorkTime workTime ){
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setSurname(lastName);
        employee.setCode(code);
        employee.setEmploymentDate(employmentDate);
        employee.setWorkTime(workTime);
        employee.setJudgment(isJudgment);
        employee.setWorkFunction( workFunction );
        employee.setDepartments( createEmployeeDepartments(employee , departmentList) );
        return employee;
    }

    private List<EmployeeDepartment> createEmployeeDepartments( Employee employee , List<Department> departmentList  ){
        List<EmployeeDepartment> list = new ArrayList<>();
        for (Department department : departmentList) {
            list.add( new EmployeeDepartment( employee , department ) );
        }
        return list;
    }

}
