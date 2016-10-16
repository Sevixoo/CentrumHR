package com.centrumhr.desktop.data;

import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.Employee;
import com.centrumhr.data.orm.IORMLiteDataBase;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class ORMLiteDatabase implements IORMLiteDataBase {

    // we are using the in-memory H2 database
    private final static String DATABASE_URL = "jdbc:sqlite:";

    private ConnectionSource connectionSource = null;

    public ORMLiteDatabase(File dbFile) {
        try {
            this.connectionSource = new JdbcConnectionSource(DATABASE_URL+dbFile.getPath());
        }catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void onCreateDataBase(){
        try {
            TableUtils.createTable(connectionSource, Employee.class );
        }catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> Dao<T, Long> provideDao(Class<T> clazz) throws DatabaseException {
        try {
            return DaoManager.createDao(connectionSource, clazz);
        }catch (SQLException ex){
            throw new DatabaseException(ex);
        }
    }

    @Override
    public void beginTransaction() {

    }

    @Override
    public void setTransactionSuccessful() {

    }

    @Override
    public void endTransaction() {

    }
}
