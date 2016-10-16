package com.centrumhr.data.repository;

import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.Employee;
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
        super(mUnitOfWork);
    }

    @Override
    public void save(Employee employee) throws DatabaseException {
        try {
            provideDao(Employee.class).createOrUpdate(employee);
        }catch (SQLException ex){
            throw  new DatabaseException(ex);
        }
    }

    @Override
    public Employee load(UUID uniqueId) throws DatabaseException{
        try {
            return provideDao(Employee.class).queryBuilder().where().eq("uniqueId",uniqueId).queryForFirst();
        }catch (SQLException ex){
            throw  new DatabaseException(ex);
        }
    }

    @Override
    public List<Employee> list() throws DatabaseException{
        try {
            return provideDao(Employee.class).queryForAll();
        }catch (SQLException ex){
            throw  new DatabaseException(ex);
        }
    }
}
