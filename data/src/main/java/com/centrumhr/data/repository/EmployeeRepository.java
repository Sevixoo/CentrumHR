package com.centrumhr.data.repository;

import com.centrumhr.data.core.DAO;
import com.centrumhr.data.core.DatabaseException;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.data.model.employment.EmployeeEntity;
import com.centrumhr.data.model.employment.IEmployeeRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 15.02.2017.
 */
public class EmployeeRepository implements IEmployeeRepository {

    private DAO<EmployeeEntity> employeeEntityDAO;

    @Inject
    public EmployeeRepository(IORMLiteDataBase dataBase) {
        employeeEntityDAO = dataBase.provideDAO(EmployeeEntity.class);
    }

    @Override
    public List<EmployeeEntity> list(List<UUID> ids) {
        try{
           return employeeEntityDAO.queryBuilder()
                    .orderBy("surname" , true )
                    .where().in("uniqueId",ids)
                    .query();
        }catch (SQLException ex){
            throw new DatabaseException(ex);
        }
    }
}
