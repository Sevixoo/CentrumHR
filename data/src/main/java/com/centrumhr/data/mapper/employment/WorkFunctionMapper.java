package com.centrumhr.data.mapper.employment;

import com.centrumhr.data.core.Mapper;
import com.centrumhr.data.model.employment.WorkFunctionEntity;
import com.centrumhr.dto.employment.WorkFunctionDTO;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class WorkFunctionMapper extends Mapper<WorkFunctionEntity,WorkFunctionDTO> {

    public static final WorkFunctionMapper INSTANCE = new WorkFunctionMapper();

    @Override
    public WorkFunctionEntity forward(WorkFunctionDTO workFunctionDTO) {
        if(workFunctionDTO==null)return null;
        return new WorkFunctionEntity(
                workFunctionDTO.getUniqueId(),
                workFunctionDTO.getName()
        );
    }

    @Override
    public WorkFunctionDTO backward(WorkFunctionEntity workFunctionEntity) {
        if(workFunctionEntity ==null)return null;
        return new WorkFunctionDTO(
                workFunctionEntity.getUniqueId(),
                workFunctionEntity.getName()
        );
    }
}
