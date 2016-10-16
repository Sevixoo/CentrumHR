package com.centrumhr.application.application.employee;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.employee.usecase.EditEmployeeUseCase;
import com.centrumhr.application.application.employee.usecase.GetEmployeeUseCase;
import com.centrumhr.data.model.Employee;
import com.centrumhr.data.domain.IEmployeeRepository;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
public class EmployeeUseCaseFactory {

    private IExecutor               mExecutor;
    private IHandler                mHandler;
    private IEmployeeRepository     mEmployeeRepository;

    @Inject
    public EmployeeUseCaseFactory(IExecutor mExecutor, IHandler mHandler, IEmployeeRepository mEmployeeRepository) {
        this.mExecutor = mExecutor;
        this.mHandler = mHandler;
        this.mEmployeeRepository = mEmployeeRepository;
    }

    public com.centrumhr.application.application.employee.usecase.AddEmployeeUseCase createAddEmployeeUseCase(Employee employee){
        return new com.centrumhr.application.application.employee.usecase.AddEmployeeUseCase(mExecutor,mHandler,employee,mEmployeeRepository);
    }

    public com.centrumhr.application.application.employee.usecase.LoadEmployeesUseCase createLoadEmployeesUseCase(){
        return new com.centrumhr.application.application.employee.usecase.LoadEmployeesUseCase(mExecutor,mHandler,mEmployeeRepository);
    }

    public EditEmployeeUseCase createEditEmployeeUseCase(Employee toUpdate , Employee data ){
        return new EditEmployeeUseCase( mExecutor,mHandler,mEmployeeRepository  , toUpdate , data );
    }

    public GetEmployeeUseCase createGetEmployeeUseCase(UUID uuid){
        return new GetEmployeeUseCase( mExecutor,mHandler,  uuid , mEmployeeRepository );
    }

}
