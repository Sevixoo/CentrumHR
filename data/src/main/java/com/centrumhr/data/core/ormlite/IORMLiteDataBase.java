package com.centrumhr.data.core.ormlite;

import com.centrumhr.data.core.DatabaseException;

/**
 * Created by Seweryn on 02.10.2016.
 */
public interface IORMLiteDataBase{

    <T> DAO<T> provideDAO(Class<T> clazz) throws DatabaseException;

    void beginTransaction() ;

    void setTransactionSuccessful() ;

    void endTransaction() ;

}
