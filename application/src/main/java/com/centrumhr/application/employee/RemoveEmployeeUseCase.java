package com.centrumhr.application.employee;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.model.employment.EmployeeEntity;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Seweryn on 09.11.2016.
 */
public class RemoveEmployeeUseCase extends UseCase<UUID,Boolean> {

    private IORMLiteDataBase dataBase;

    @Inject
    public RemoveEmployeeUseCase(IORMLiteDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Boolean execute(UUID employeeToRemove) throws DomainException {
        dataBase.provideDAO(EmployeeEntity.class).delete(employeeToRemove);
        return true;
    }

}
