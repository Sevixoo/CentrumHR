package com.centrumhr.data.importer;

import com.centrumhr.data.core.ormlite.DAO;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.core.DatabaseException;
import com.centrumhr.data.model.employment.*;

import javax.inject.Inject;

/**
 * Created by Seweryn on 25.10.2016.
 */
public class EmployeeImporter {

    private DAO<DepartmentEntity> departmentDAO;
    private DAO<EmployeeEntity> employeeDAO;
    private DAO<WorkFunctionEntity> workFunctionDAO;
    private DAO<EmployeeDepartmentEntity> employeeDepartmentDAO;

    @Inject
    public EmployeeImporter(IORMLiteDataBase dataBase) {
        departmentDAO = dataBase.provideDAO(DepartmentEntity.class);
        employeeDAO= dataBase.provideDAO(EmployeeEntity.class);
        workFunctionDAO =dataBase.provideDAO(WorkFunctionEntity.class);
        employeeDepartmentDAO =dataBase.provideDAO(EmployeeDepartmentEntity.class);
    }

    public void importData(EmployeeEntity employeeEntityDTO)throws DatabaseException{

        EmployeeEntity employeeEntity = employeeDAO.load(employeeEntityDTO.getUniqueId());
        if(employeeEntity ==null){
            employeeEntity = employeeEntityDTO;
        }else{
            employeeEntity.update(employeeEntityDTO);
        }

        WorkFunctionEntity workFunctionEntity = workFunctionDAO.load(employeeEntity.getWorkFunctionEntity().getUniqueId());
        employeeEntity.setWorkFunctionEntity(workFunctionEntity);

        employeeDAO.save(employeeEntity);

        if( employeeEntityDTO.getDepartments() != null ){

            for ( EmployeeDepartmentEntity employeeDepartmentEntity : employeeEntity.getDepartments() ){
                boolean remove = true;
                for (EmployeeDepartmentEntity employeeDepartmentEntityDTO : employeeEntityDTO.getDepartments()){
                    if(employeeDepartmentEntityDTO.equals(employeeDepartmentEntity)){
                        remove = false;
                        break;
                    }
                }
                if(remove){
                    employeeDepartmentDAO.deleteItem(employeeDepartmentEntity);
                }
            }

            for (EmployeeDepartmentEntity employeeDepartmentEntityDTO : employeeEntityDTO.getDepartments()){
                DepartmentEntity departmentEntity = departmentDAO.load(employeeDepartmentEntityDTO.getDepartmentEntity().getUniqueId());
                employeeDepartmentEntityDTO.setEmployeeEntity(employeeEntity);
                employeeDepartmentEntityDTO.setDepartmentEntity(departmentEntity);
                employeeDepartmentDAO.save(employeeDepartmentEntityDTO);
            }
        }

    }

}
