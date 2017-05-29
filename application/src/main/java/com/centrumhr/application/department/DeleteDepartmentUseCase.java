package com.centrumhr.application.department;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class DeleteDepartmentUseCase extends UseCase<UUID,UUID> {

    private IORMLiteDataBase dataBase;

    @Inject
    public DeleteDepartmentUseCase(IORMLiteDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public UUID execute(UUID uuid) throws DomainException {
        dataBase.provideDAO(DepartmentEntity.class).delete(uuid);
        return uuid;
    }

}
