package com.centrumhr.desktop.data;

import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendanceEmployee;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.employment.Department;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.model.employment.EmployeeDepartment;
import com.centrumhr.data.model.employment.WorkFunction;
import com.centrumhr.data.model.settings.SettingItem;
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
            TableUtils.createTable(connectionSource, Department.class );
            TableUtils.createTable(connectionSource, EmployeeDepartment.class );
            TableUtils.createTable(connectionSource, WorkFunction.class );
            TableUtils.createTable(connectionSource, SettingItem.class );
            TableUtils.createTable(connectionSource, AttendancePlan.class );
            TableUtils.createTable(connectionSource, AttendanceDay.class );
            TableUtils.createTable(connectionSource, AttendanceEmployee.class );

            provideDao(WorkFunction.class).createOrUpdate(new WorkFunction("Kasjer"));
            provideDao(WorkFunction.class).createOrUpdate(new WorkFunction("Sprzątaczka"));
            provideDao(WorkFunction.class).createOrUpdate(new WorkFunction("Magazynier"));
            provideDao(WorkFunction.class).createOrUpdate(new WorkFunction("Kierownik zmiany"));

        }catch (SQLException|DatabaseException ex){
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
