package com.centrumhr.data.repository;

import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.orm.Repository;
import com.centrumhr.data.orm.UnitOfWork;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class EmployeeRepository extends Repository<Employee> implements IEmployeeRepository {

    public EmployeeRepository(UnitOfWork mUnitOfWork) {
        super( Employee.class , mUnitOfWork);
    }

    @Override
    public List<Employee> list(List<UUID> uniqueIds) throws DatabaseException {
        try{
            return provideDao(Employee.class)
                    .queryBuilder()
                    .where()
                    .in( "uniqueId" , uniqueIds )
                    .query();

        }catch (SQLException ex){
            throw new DatabaseException(ex);
        }
    }
}
