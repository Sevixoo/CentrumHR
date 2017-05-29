package com.centrumhr.application.department;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.mapper.employment.DepartmentMapper;
import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.employment.DepartmentDTO;

import javax.inject.Inject;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class AddDepartmentUseCase extends UseCase<String,DepartmentDTO> {

    private IORMLiteDataBase dataBase;

    @Inject
    public AddDepartmentUseCase(IORMLiteDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public DepartmentDTO execute(String name) throws DomainException {
        DepartmentEntity departmentEntity = new DepartmentEntity(name);
        dataBase.provideDAO(DepartmentEntity.class).save(departmentEntity);
        return DepartmentMapper.INSTANCE.backward(departmentEntity);
    }

}
