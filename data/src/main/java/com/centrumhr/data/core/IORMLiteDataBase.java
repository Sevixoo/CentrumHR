package com.centrumhr.data.core;

import com.j256.ormlite.dao.Dao;

/**
 * Created by Seweryn on 02.10.2016.
 */
public interface IORMLiteDataBase{

    <T> DAO<T> provideDAO(Class<T> clazz) throws DatabaseException;

    void beginTransaction() ;

    void setTransactionSuccessful() ;

    void endTransaction() ;

}
