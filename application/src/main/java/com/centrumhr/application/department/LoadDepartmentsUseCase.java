package com.centrumhr.application.department;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.data.mapper.employment.DepartmentMapper;
import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.employment.DepartmentDTO;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class LoadDepartmentsUseCase extends UseCase<Void,List<DepartmentDTO>> {

    private IORMLiteDataBase dataBase;

    @Inject
    public LoadDepartmentsUseCase(IORMLiteDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public List<DepartmentDTO> execute(Void a) throws DomainException {
        return DepartmentMapper.INSTANCE.backward(dataBase.provideDAO(DepartmentEntity.class).list());
    }
}
