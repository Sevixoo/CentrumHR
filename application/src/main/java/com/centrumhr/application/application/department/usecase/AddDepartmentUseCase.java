package com.centrumhr.application.application.department.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IDepartmentRepository;
import com.centrumhr.data.model.employment.Department;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class AddDepartmentUseCase extends UseCase<Department> {

    private String name;
    private IDepartmentRepository repository;

    public AddDepartmentUseCase(IExecutor executor, IHandler handler,IDepartmentRepository repository, String name) {
        super(executor, handler);
        this.name = name;
        this.repository = repository;
    }

    @Override
    public Department execute() throws Exception {
        Department department = new Department(name);
        repository.save(department);
        return department;
    }
}
