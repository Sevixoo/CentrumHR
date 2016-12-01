package com.centrumhr.data.importer;

import com.centrumhr.data.domain.IDepartmentRepository;
import com.centrumhr.data.domain.IEmployeeDepartmentRepository;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.domain.IWorkFunctionRepository;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.model.employment.EmployeeDepartment;

/**
 * Created by Seweryn on 25.10.2016.
 */
public class EmployeeImporter {

    private IDepartmentRepository mDepartmentRepository;
    private IEmployeeRepository mEmployeeRepository;
    private IWorkFunctionRepository mWorkFunctionRepository;
    private IEmployeeDepartmentRepository mIEmployeeDepartmentRepository;

    public EmployeeImporter(IDepartmentRepository mDepartmentRepository, IEmployeeRepository mEmployeeRepository, IWorkFunctionRepository mWorkFunctionRepository, IEmployeeDepartmentRepository employeeDepartmentRepository) {
        this.mDepartmentRepository = mDepartmentRepository;
        this.mEmployeeRepository = mEmployeeRepository;
        this.mWorkFunctionRepository = mWorkFunctionRepository;
        this.mIEmployeeDepartmentRepository = employeeDepartmentRepository;
    }

    public void importData(Employee employeeDTO)throws DatabaseException{

        Employee employee = mEmployeeRepository.load(employeeDTO.getUniqueId());
        if(employee==null){
            employee = employeeDTO;
        }else{
            employee.update( employeeDTO );
        }
        mEmployeeRepository.save(employee);

        if( employeeDTO.getDepartments() != null ){

            for ( EmployeeDepartment employeeDepartment : employee.getDepartments() ){
                boolean remove = true;
                for (EmployeeDepartment employeeDepartmentDTO : employeeDTO.getDepartments()){
                    if(employeeDepartmentDTO.equals(employeeDepartment)){
                        remove = false;
                        break;
                    }
                }
                if(remove){
                    mIEmployeeDepartmentRepository.delete(employeeDepartment.getUniqueId());
                }
            }

            for (EmployeeDepartment employeeDepartmentDTO : employeeDTO.getDepartments()){
                EmployeeDepartment employeeDepartment = mIEmployeeDepartmentRepository.load(employeeDepartmentDTO.getUniqueId());
                if( employeeDepartment == null ){
                    employeeDepartment = employeeDepartmentDTO;
                }else{
                    employeeDepartment.update(employeeDepartmentDTO);
                }
                employeeDepartment.setEmployee(employee);
                employeeDepartment.setDepartment(employeeDepartmentDTO.getDepartment());
                mIEmployeeDepartmentRepository.save(employeeDepartment);
            }
        }

    }

}
