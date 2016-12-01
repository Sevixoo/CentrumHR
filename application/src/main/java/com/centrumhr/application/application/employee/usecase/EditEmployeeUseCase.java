package com.centrumhr.application.application.employee.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.employee.dto.PersonalDataDTO;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.importer.EmployeeImporter;
import com.centrumhr.data.model.employment.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class EditEmployeeUseCase extends UseCase<Employee> {

    private PersonalDataDTO         personalDataDTO;
    private List<Department>        departments;
    private WorkFunction            workFunction;
    private EmployeeFactory         employeeFactory;
    private Date                    employmentDate;
    private boolean                 isJudgment;
    private WorkTime                workTime;
    private EmployeeImporter        employeeImporter;
    private Employee                employee;

    public EditEmployeeUseCase(IExecutor executor, IHandler handler, EmployeeImporter employeeImporter, EmployeeFactory employeeFactory, PersonalDataDTO personalDataDTO, WorkFunction workFunction, List<Department> departments , Date employmentDate , boolean isJudgment , WorkTime workTime , Employee employee) {
        super(executor, handler);
        this.personalDataDTO = personalDataDTO;
        this.departments = departments;
        this.workFunction = workFunction;
        this.employeeFactory = employeeFactory;
        this.employmentDate = employmentDate;
        this.isJudgment = isJudgment;
        this.workTime = workTime;
        this.employeeImporter = employeeImporter;
        this.employee = employee;
    }


    @Override
    public Employee execute() throws Exception {
        Employee employeeDto = employeeFactory.createEmployee(
                employee,
                personalDataDTO.getFirstName(),
                personalDataDTO.getFirstName(),
                personalDataDTO.getCode(),
                workFunction,
                departments,
                employmentDate,
                isJudgment,
                workTime
        );
        employeeImporter.importData(employeeDto);
        return employee;
    }
}
