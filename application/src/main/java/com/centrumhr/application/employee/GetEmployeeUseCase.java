package com.centrumhr.application.employee;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.data.mapper.employment.EmployeeMapper;
import com.centrumhr.data.model.employment.EmployeeEntity;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.employment.EmployeeDTO;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class GetEmployeeUseCase extends UseCase<UUID,EmployeeDTO> {

    private IORMLiteDataBase dataBase;

    @Inject
    public GetEmployeeUseCase(IORMLiteDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public EmployeeDTO execute(UUID employeeID) throws DomainException {
        EmployeeEntity employeeEntity = dataBase.provideDAO(EmployeeEntity.class).load( employeeID );
        if(employeeEntity == null){
            throw new DomainException("employeeEntity not found uuid:" + employeeID.toString());
        }
        return EmployeeMapper.INSTANCE.backward(employeeEntity);
    }

}
