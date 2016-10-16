package com.centrumhr.application.application.employee.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.model.Employee;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class EditEmployeeUseCase extends UseCase<Employee> {

    private IEmployeeRepository employeeRepository;
    private Employee employeeToUpdate;
    private Employee data;

    public EditEmployeeUseCase(IExecutor executor, IHandler handler, IEmployeeRepository employeeRepository, Employee toUpdate , Employee data) {
        super(executor, handler);
        this.employeeRepository = employeeRepository;
        this.employeeToUpdate = toUpdate;
        this.data = data;
    }

    @Override
    public Employee execute() throws Exception {
        employeeToUpdate.update( data );
        employeeRepository.save( employeeToUpdate );
        return employeeToUpdate;
    }
}
