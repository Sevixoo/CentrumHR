package com.centrumhr.application.application.employee.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.Employee;

import java.util.UUID;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class GetEmployeeUseCase extends UseCase<Employee> {

    private UUID employeeID;
    private IEmployeeRepository employeeRepository;

    public GetEmployeeUseCase(IExecutor executor, IHandler handler, UUID employeeID, IEmployeeRepository employeeRepository) {
        super(executor, handler);
        this.employeeID = employeeID;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee execute() throws Exception {
        Employee employee = employeeRepository.load( employeeID );
        if(employee == null){
            throw new DatabaseException("employee not found uuid:" + employeeID.toString());
        }
        return employee;
    }
}
