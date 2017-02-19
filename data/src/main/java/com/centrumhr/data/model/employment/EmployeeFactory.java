package com.centrumhr.data.model.employment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.centrumhr.data.mapper.employment.DepartmentMapper;
import com.centrumhr.data.mapper.employment.WorkFunctionMapper;
import com.centrumhr.dto.employment.DepartmentDTO;
import com.centrumhr.dto.employment.WorkFunctionDTO;
import com.centrumhr.dto.employment.WorkTime;

import javax.inject.Inject;

/**
 * Created by Seweryn on 25.10.2016.
 */
public class EmployeeFactory {

    @Inject
    public EmployeeFactory() { }

    public EmployeeEntity createEmployee(EmployeeEntity employeeEntity, String firstName, String lastName, String code, WorkFunctionDTO workFunction , List<DepartmentDTO> departments, Date employmentDate , boolean isJudgment , WorkTime workTime ){
        employeeEntity.setFirstName(firstName);
        employeeEntity.setSurname(lastName);
        employeeEntity.setCode(code);
        employeeEntity.setEmploymentDate(employmentDate);
        employeeEntity.setWorkTime(workTime);
        employeeEntity.setJudgment(isJudgment);
        employeeEntity.setWorkFunctionEntity(WorkFunctionMapper.INSTANCE.forward(workFunction));
        employeeEntity.setDepartments( createEmployeeDepartments(employeeEntity, departments) );
        return employeeEntity;
    }

    public EmployeeEntity createEmployee(String firstName, String lastName, String code, WorkFunctionDTO workFunction, List<DepartmentDTO> departmentEntityList, Date employmentDate , boolean isJudgment , WorkTime workTime ){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(firstName);
        employeeEntity.setSurname(lastName);
        employeeEntity.setCode(code);
        employeeEntity.setEmploymentDate(employmentDate);
        employeeEntity.setWorkTime(workTime);
        employeeEntity.setJudgment(isJudgment);
        employeeEntity.setWorkFunctionEntity(WorkFunctionMapper.INSTANCE.forward(workFunction));
        employeeEntity.setDepartments( createEmployeeDepartments(employeeEntity, departmentEntityList) );
        return employeeEntity;
    }

    private List<EmployeeDepartmentEntity> createEmployeeDepartments(EmployeeEntity employeeEntity, List<DepartmentDTO> departmentDTOs){
        if(departmentDTOs==null)return null;
        List<EmployeeDepartmentEntity> list = new ArrayList<>();
        List<DepartmentEntity> entities = DepartmentMapper.INSTANCE.forward(departmentDTOs);
        for (DepartmentEntity departmentEntity : entities) {
            list.add( new EmployeeDepartmentEntity(employeeEntity, departmentEntity) );
        }
        return list;
    }

}
