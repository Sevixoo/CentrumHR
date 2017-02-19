package com.centrumhr.application.workFunction;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.data.mapper.employment.WorkFunctionMapper;
import com.centrumhr.data.model.employment.WorkFunctionEntity;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.employment.WorkFunctionDTO;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Seweryn on 25.10.2016.
 */
public class LoadWorkFunctionsUseCase extends UseCase<Boolean,List<WorkFunctionDTO>> {

    private IORMLiteDataBase dataBase;

    @Inject
    public LoadWorkFunctionsUseCase(IORMLiteDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public List<WorkFunctionDTO> execute(Boolean aBoolean) throws DomainException {
        return WorkFunctionMapper.INSTANCE.backward(dataBase.provideDAO(WorkFunctionEntity.class).list());
    }

}
