package com.centrumhr.data.mapper.employment;

import com.centrumhr.data.core.Mapper;
import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.dto.employment.DepartmentDTO;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class DepartmentMapper extends Mapper<DepartmentEntity,DepartmentDTO> {

    public static final DepartmentMapper INSTANCE = new DepartmentMapper();

    @Override
    public DepartmentEntity forward(DepartmentDTO departmentDTO) {
        if(departmentDTO==null)return null;
        return new DepartmentEntity(
                departmentDTO.getUniqueId(),
                departmentDTO.getName()
        );
    }

    @Override
    public DepartmentDTO backward(DepartmentEntity departmentEntity) {
        if(departmentEntity ==null)return null;
        return new DepartmentDTO(
                departmentEntity.getUniqueId(),
                departmentEntity.getName()
        );
    }
}
