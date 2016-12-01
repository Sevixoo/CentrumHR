package com.centrumhr.data.repository;

import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.domain.IWorkFunctionRepository;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.model.employment.WorkFunction;
import com.centrumhr.data.orm.Repository;
import com.centrumhr.data.orm.UnitOfWork;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class WorkFunctionRepository extends Repository<WorkFunction> implements IWorkFunctionRepository {

    public WorkFunctionRepository(UnitOfWork mUnitOfWork) {
        super( WorkFunction.class , mUnitOfWork);
    }

}
