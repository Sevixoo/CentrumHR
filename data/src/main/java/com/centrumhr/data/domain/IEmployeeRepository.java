package com.centrumhr.data.domain;

import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.Employee;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
public interface IEmployeeRepository{

    void save( Employee employee )throws DatabaseException;

    Employee load( UUID uniqueId )throws DatabaseException;

    List<Employee> list()throws DatabaseException;
}
