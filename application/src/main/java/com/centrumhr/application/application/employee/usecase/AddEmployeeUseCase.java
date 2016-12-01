package com.centrumhr.application.application.employee.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.employee.dto.PersonalDataDTO;
import com.centrumhr.data.importer.EmployeeImporter;
import com.centrumhr.data.model.employment.*;
import com.centrumhr.data.domain.IEmployeeRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Seweryn on 28.09.2016.
 */
public class AddEmployeeUseCase extends UseCase<Employee> {

    private PersonalDataDTO         personalDataDTO;
    private List<Department>        departments;
    private WorkFunction            workFunction;
    private EmployeeFactory         employeeFactory;
    private Date                    employmentDate;
    private boolean                 isJudgment;
    private WorkTime                workTime;
    private EmployeeImporter        employeeImporter;


    public AddEmployeeUseCase(IExecutor executor, IHandler handler,EmployeeImporter employeeImporter, EmployeeFactory employeeFactory, PersonalDataDTO personalDataDTO, WorkFunction workFunction, List<Department> departments ,  Date employmentDate , boolean isJudgment , WorkTime workTime) {
        super(executor, handler);
        this.personalDataDTO = personalDataDTO;
        this.departments = departments;
        this.workFunction = workFunction;
        this.employeeFactory = employeeFactory;
        this.employmentDate = employmentDate;
        this.isJudgment = isJudgment;
        this.workTime = workTime;
        this.employeeImporter = employeeImporter;
    }

    @Override
    public Employee execute() throws Exception {
        Employee employee = employeeFactory.createEmployee(
                personalDataDTO.getFirstName(),
                personalDataDTO.getFirstName(),
                personalDataDTO.getCode(),
                workFunction,
                departments,
                employmentDate,
                isJudgment,
                workTime
        );
        employeeImporter.importData(employee);
        return employee;
    }
}
