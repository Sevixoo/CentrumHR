package com.centrumhr.data.orm;

import com.centrumhr.data.exception.DatabaseException;
import com.j256.ormlite.dao.Dao;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class UnitOfWork {

    private IORMLiteDataBase mORMLiteDataBase;

    public UnitOfWork(IORMLiteDataBase mORMLiteDataBase) {
        this.mORMLiteDataBase = mORMLiteDataBase;
    }

    public <T> Dao<T, Long> provideDao(Class<T> clazz) throws DatabaseException {
        return mORMLiteDataBase.provideDao(clazz);
    }
}
