package com.centrumhr.data.repository;

import com.centrumhr.data.domain.IDepartmentRepository;
import com.centrumhr.data.domain.IEmployeeDepartmentRepository;
import com.centrumhr.data.model.employment.Department;
import com.centrumhr.data.model.employment.EmployeeDepartment;
import com.centrumhr.data.orm.Repository;
import com.centrumhr.data.orm.UnitOfWork;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class EmployeeDepartmentRepository extends Repository<EmployeeDepartment> implements IEmployeeDepartmentRepository {

    public EmployeeDepartmentRepository(UnitOfWork mUnitOfWork) {
        super(EmployeeDepartment.class, mUnitOfWork);
    }
}
