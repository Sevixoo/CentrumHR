package com.centrumhr.data.domain;

import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.employment.Department;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.model.employment.EmployeeDepartment;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
public interface IEmployeeDepartmentRepository {

    void save(EmployeeDepartment department)throws DatabaseException;

    EmployeeDepartment load(UUID uniqueId)throws DatabaseException;

    List<EmployeeDepartment> list()throws DatabaseException;

    void delete(UUID uniqueId)throws DatabaseException;
}
