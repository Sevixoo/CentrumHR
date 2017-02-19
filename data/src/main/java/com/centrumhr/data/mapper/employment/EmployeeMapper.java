package com.centrumhr.data.mapper.employment;

import com.centrumhr.data.core.Mapper;
import com.centrumhr.data.model.employment.*;
import com.centrumhr.dto.employment.EmployeeDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class EmployeeMapper extends Mapper<EmployeeEntity,EmployeeDTO> {

    public static final EmployeeMapper INSTANCE = new EmployeeMapper();

    @Override
    public EmployeeEntity forward(EmployeeDTO employeeDTO) {
        if(employeeDTO==null)return null;

        Collection<DepartmentEntity> departments = DepartmentMapper.INSTANCE.forward(employeeDTO.getDepartments());
        Collection<EmployeeDepartmentEntity> employeeDepartments = new ArrayList<>();

        EmployeeEntity employeeEntity = new EmployeeEntity(
                employeeDTO.getUniqueId(),
                employeeDTO.getFirstName(),
                employeeDTO.getSurname(),
                employeeDTO.getCode(),
                employeeDTO.getWorkTime(),
                employeeDTO.isJudgment(),
                employeeDTO.getEmploymentDate(),
                WorkFunctionMapper.INSTANCE.forward(employeeDTO.getWorkFunction()),
                employeeDepartments
        );

        for (DepartmentEntity department : departments) {
            employeeDepartments.add( new EmployeeDepartmentEntity( employeeEntity , department ) );
        }
        return employeeEntity;
    }

    @Override
    public EmployeeDTO backward(EmployeeEntity employeeEntity) {
        if(employeeEntity ==null)return null;

        Collection<EmployeeDepartmentEntity> employeeDepartments = employeeEntity.getDepartments();
        Collection<DepartmentEntity> departments = null;
        if(employeeDepartments!=null) {
            departments = new ArrayList<>();
            for (EmployeeDepartmentEntity employeeDepartment : employeeDepartments) {
                departments.add(employeeDepartment.getDepartmentEntity());
            }
        }

        return new EmployeeDTO(
                employeeEntity.getUniqueId(),
                employeeEntity.getFirstName(),
                employeeEntity.getSurname(),
                employeeEntity.getCode(),
                employeeEntity.getWorkTime(),
                employeeEntity.isJudgment(),
                employeeEntity.getEmploymentDate(),
                WorkFunctionMapper.INSTANCE.backward(employeeEntity.getWorkFunctionEntity()),
                DepartmentMapper.INSTANCE.backward(departments)
        );
    }
}
