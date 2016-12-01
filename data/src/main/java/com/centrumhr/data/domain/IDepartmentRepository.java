package com.centrumhr.data.domain;

import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.employment.Department;
import com.centrumhr.data.model.employment.Employee;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
public interface IDepartmentRepository {

    void save(Department department)throws DatabaseException;

    Department load(UUID uniqueId)throws DatabaseException;

    List<Department> list()throws DatabaseException;

    void delete( UUID uniqueId )throws DatabaseException;
}
