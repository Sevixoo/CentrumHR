package com.centrumhr.application.application.employee.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.model.employment.Employee;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 09.11.2016.
 */
public class RemoveEmployeeUseCase extends UseCase<Boolean> {

    private UUID employeeToRemove;
    private IEmployeeRepository mEmployeeRepository;

    public RemoveEmployeeUseCase(IExecutor executor, IHandler handler, IEmployeeRepository mEmployeeRepository, UUID employeeToRemove ) {
        super(executor, handler);
        this.mEmployeeRepository = mEmployeeRepository;
        this.employeeToRemove = employeeToRemove;
    }

    @Override
    public Boolean execute() throws Exception {
        mEmployeeRepository.delete(employeeToRemove);
        return true;

    }
}
