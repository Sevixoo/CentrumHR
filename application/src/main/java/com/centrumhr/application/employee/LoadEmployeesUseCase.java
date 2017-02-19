package com.centrumhr.application.employee;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.data.mapper.employment.EmployeeMapper;
import com.centrumhr.data.model.employment.EmployeeEntity;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.employment.EmployeeDTO;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class LoadEmployeesUseCase extends UseCase<Boolean,List<EmployeeDTO>> {

    private IORMLiteDataBase dataBase;

    @Inject
    public LoadEmployeesUseCase(IORMLiteDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public List<EmployeeDTO> execute(Boolean aBoolean) throws DomainException {
        return EmployeeMapper.INSTANCE.backward(dataBase.provideDAO(EmployeeEntity.class).list());
    }

}
