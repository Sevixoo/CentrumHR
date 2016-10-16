package com.centrumhr.application.application.employee.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.model.Employee;

import java.util.List;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class LoadEmployeesUseCase extends UseCase<List<Employee>> {

    private IEmployeeRepository mEmployeeRepository;

    public LoadEmployeesUseCase(IExecutor executor, IHandler handler, IEmployeeRepository mEmployeeRepository) {
        super(executor, handler);
        this.mEmployeeRepository = mEmployeeRepository;
    }

    @Override
    public List<Employee> execute() throws Exception {
        return mEmployeeRepository.list();
    }
}
