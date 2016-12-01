package com.centrumhr.data.repository;

import com.centrumhr.data.domain.IDepartmentRepository;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.employment.Department;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.orm.Repository;
import com.centrumhr.data.orm.UnitOfWork;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class DepartmentRepository extends Repository<Department> implements IDepartmentRepository {

    public DepartmentRepository(UnitOfWork mUnitOfWork) {
        super(Department.class, mUnitOfWork);
    }
}
