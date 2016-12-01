package com.centrumhr.data.orm;

import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.employment.Employee;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 02.10.2016.
 */
public abstract class Repository<T> {

    private UnitOfWork mUnitOfWork;
    private Class<T> clazz;

    public Repository(Class<T> clazz , UnitOfWork mUnitOfWork) {
        this.mUnitOfWork = mUnitOfWork;
        this.clazz = clazz;
    }

    public <T> Dao<T, Long> provideDao(Class<T> clazz) throws DatabaseException {
        return mUnitOfWork.provideDao(clazz);
    }

    public void save(T item) throws DatabaseException {
        try {
            provideDao(clazz).createOrUpdate(item);
        }catch (SQLException ex){
            throw  new DatabaseException(ex);
        }
    }

    public void delete(UUID uuid) throws DatabaseException {
        try {
            DeleteBuilder deleteBuilder = provideDao(clazz).deleteBuilder();
            deleteBuilder.where().eq("uniqueId",uuid);
            deleteBuilder.delete();
        }catch (SQLException ex){
            throw  new DatabaseException(ex);
        }
    }

    public T load(UUID uniqueId) throws DatabaseException{
        try {
            return provideDao(clazz).queryBuilder().where().eq("uniqueId",uniqueId).queryForFirst();
        }catch (SQLException ex){
            throw  new DatabaseException(ex);
        }
    }

    public List<T> list() throws DatabaseException{
        try {
            return provideDao(clazz).queryForAll();
        }catch (SQLException ex){
            throw  new DatabaseException(ex);
        }
    }


}
