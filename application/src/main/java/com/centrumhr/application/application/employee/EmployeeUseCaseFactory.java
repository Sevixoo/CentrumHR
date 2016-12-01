package com.centrumhr.application.application.employee;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.employee.dto.PersonalDataDTO;
import com.centrumhr.application.application.employee.usecase.EditEmployeeUseCase;
import com.centrumhr.application.application.employee.usecase.GetEmployeeUseCase;
import com.centrumhr.application.application.employee.usecase.RemoveEmployeeUseCase;
import com.centrumhr.data.importer.EmployeeImporter;
import com.centrumhr.data.model.employment.*;
import com.centrumhr.data.domain.IEmployeeRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
public class EmployeeUseCaseFactory {

    private IExecutor               mExecutor;
    private IHandler                mHandler;
    private IEmployeeRepository     mEmployeeRepository;
    private EmployeeFactory         mEmployeeFactory;
    private EmployeeImporter        mEmployeeImporter;

    @Inject
    public EmployeeUseCaseFactory(IExecutor mExecutor, IHandler mHandler,EmployeeFactory employeeFactory,EmployeeImporter employeeImporter, IEmployeeRepository mEmployeeRepository) {
        this.mExecutor = mExecutor;
        this.mHandler = mHandler;
        this.mEmployeeRepository = mEmployeeRepository;
        this.mEmployeeFactory = employeeFactory;
        this.mEmployeeImporter = employeeImporter;
    }

    public com.centrumhr.application.application.employee.usecase.AddEmployeeUseCase createAddEmployeeUseCase( PersonalDataDTO data, WorkFunction workFunction, List<Department> departments, Date employmentDate , boolean isJudgment , WorkTime workTime ){
        return new com.centrumhr.application.application.employee.usecase.AddEmployeeUseCase(mExecutor,mHandler,mEmployeeImporter,mEmployeeFactory,data,workFunction, departments ,employmentDate,isJudgment,workTime);
    }

    public com.centrumhr.application.application.employee.usecase.LoadEmployeesUseCase createLoadEmployeesUseCase(){
        return new com.centrumhr.application.application.employee.usecase.LoadEmployeesUseCase(mExecutor,mHandler,mEmployeeRepository);
    }

    public EditEmployeeUseCase createEditEmployeeUseCase( PersonalDataDTO data, WorkFunction workFunction, List<Department> departments, Date employmentDate , boolean isJudgment , WorkTime workTime , Employee employee ){
        return new EditEmployeeUseCase( mExecutor,mHandler,mEmployeeImporter,mEmployeeFactory,data,workFunction, departments ,employmentDate,isJudgment,workTime , employee );
    }

    public GetEmployeeUseCase createGetEmployeeUseCase(UUID uuid){
        return new GetEmployeeUseCase( mExecutor,mHandler,  uuid , mEmployeeRepository );
    }

    public RemoveEmployeeUseCase createRemoveEmployeeUseCase(UUID uuid){
        return new RemoveEmployeeUseCase( mExecutor,mHandler, mEmployeeRepository , uuid );
    }

}
