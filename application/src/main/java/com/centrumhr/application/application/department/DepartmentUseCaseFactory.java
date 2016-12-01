package com.centrumhr.application.application.department;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.department.usecase.AddDepartmentUseCase;
import com.centrumhr.application.application.department.usecase.DeleteDepartmentUseCase;
import com.centrumhr.application.application.department.usecase.LoadDepartmentsUseCase;
import com.centrumhr.data.domain.IDepartmentRepository;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class DepartmentUseCaseFactory {

    private IExecutor executor;
    private IHandler handler;
    private IDepartmentRepository repository;

    @Inject
    public DepartmentUseCaseFactory(IExecutor executor, IHandler handler, IDepartmentRepository repository) {
        this.executor = executor;
        this.handler = handler;
        this.repository = repository;
    }

    public AddDepartmentUseCase createAddDepartmentUseCase(String name ){
        return new AddDepartmentUseCase( executor , handler , repository, name );
    }

    public LoadDepartmentsUseCase createLoadDepartmentsUseCase(){
        return new LoadDepartmentsUseCase(executor , handler , repository);
    }

    public DeleteDepartmentUseCase createDeleteDepartmentUseCase(UUID uuid){
        return new DeleteDepartmentUseCase( executor , handler , repository, uuid );
    }

}
