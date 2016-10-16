package com.centrumhr.data.orm;

import com.centrumhr.data.exception.DatabaseException;
import com.j256.ormlite.dao.Dao;

/**
 * Created by Seweryn on 02.10.2016.
 */
public abstract class Repository<T> {

    private UnitOfWork mUnitOfWork;

    public Repository(UnitOfWork mUnitOfWork) {
        this.mUnitOfWork = mUnitOfWork;
    }

    public <T> Dao<T, Long> provideDao(Class<T> clazz) throws DatabaseException {
        return mUnitOfWork.provideDao(clazz);
    }
}
