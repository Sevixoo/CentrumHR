package com.centrumhr.application.application.employee.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.model.Employee;
import com.centrumhr.data.domain.IEmployeeRepository;

/**
 * Created by Seweryn on 28.09.2016.
 */
public class AddEmployeeUseCase extends UseCase<Employee> {

    private Employee                employee;
    private IEmployeeRepository     employeeRepository;

    public AddEmployeeUseCase(IExecutor executor, IHandler handler, Employee employee, IEmployeeRepository employeeRepository) {
        super(executor, handler);
        this.employee = employee;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee execute() throws Exception {
        employeeRepository.save(employee);
        return employee;
    }
}
