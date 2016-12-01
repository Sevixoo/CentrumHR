package com.centrumhr.application.application.department.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IDepartmentRepository;
import com.centrumhr.data.model.employment.Department;

import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class DeleteDepartmentUseCase extends UseCase<Boolean> {

    private UUID uuid;
    private IDepartmentRepository repository;

    public DeleteDepartmentUseCase(IExecutor executor, IHandler handler, IDepartmentRepository repository, UUID uuid) {
        super(executor, handler);
        this.uuid = uuid;
        this.repository = repository;
    }

    @Override
    public Boolean execute() throws Exception {
        repository.delete(uuid);
        return true;
    }
}
