package com.centrumhr.application.application.department.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IDepartmentRepository;
import com.centrumhr.data.model.employment.Department;

import java.util.List;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class LoadDepartmentsUseCase extends UseCase<List<Department>> {

    private IDepartmentRepository repository;

    public LoadDepartmentsUseCase(IExecutor executor, IHandler handler, IDepartmentRepository repository) {
        super(executor, handler);
        this.repository = repository;
    }

    @Override
    public List<Department> execute() throws Exception {
        return repository.list();
    }
}
