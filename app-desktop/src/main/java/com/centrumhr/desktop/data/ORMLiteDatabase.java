package com.centrumhr.desktop.data;

import com.centrumhr.data.core.DAO;
import com.centrumhr.data.core.DatabaseException;
import com.centrumhr.data.model.attendance.AttendanceDayEntity;
import com.centrumhr.data.model.attendance.AttendanceEmployeeEntity;
import com.centrumhr.data.model.attendance.AttendancePlanEntity;
import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.data.model.employment.EmployeeEntity;
import com.centrumhr.data.model.employment.EmployeeDepartmentEntity;
import com.centrumhr.data.model.employment.WorkFunctionEntity;
import com.centrumhr.data.model.settings.SettingItem;
import com.centrumhr.data.core.IORMLiteDataBase;
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
            TableUtils.createTable(connectionSource, EmployeeEntity.class );
            TableUtils.createTable(connectionSource, DepartmentEntity.class );
            TableUtils.createTable(connectionSource, EmployeeDepartmentEntity.class );
            TableUtils.createTable(connectionSource, WorkFunctionEntity.class );
            TableUtils.createTable(connectionSource, SettingItem.class );
            TableUtils.createTable(connectionSource, AttendancePlanEntity.class );
            TableUtils.createTable(connectionSource, AttendanceDayEntity.class );
            TableUtils.createTable(connectionSource, AttendanceEmployeeEntity.class );

            DAO<WorkFunctionEntity> workFunctionDAO = provideDAO(WorkFunctionEntity.class);
            workFunctionDAO.save(new WorkFunctionEntity("sprzedawca-kasjer 1"));
            workFunctionDAO.save(new WorkFunctionEntity("sprzedawca-kasjer 2"));
            workFunctionDAO.save(new WorkFunctionEntity("dzia³ piekarniczy"));
            workFunctionDAO.save(new WorkFunctionEntity("stoisko owoc-warz"));
            workFunctionDAO.save(new WorkFunctionEntity("fakturzysta"));
            workFunctionDAO.save(new WorkFunctionEntity("kierownik zmiany"));

            DAO<DepartmentEntity> departmentEntityDAO = provideDAO(DepartmentEntity.class);
            departmentEntityDAO.save(new DepartmentEntity("Artyku³y dzieciêce, spo¿ywcze"));
            departmentEntityDAO.save(new DepartmentEntity("Artyku³y pior¹ce i detergenty"));
            departmentEntityDAO.save(new DepartmentEntity("Cafe Bistro"));
            departmentEntityDAO.save(new DepartmentEntity("Chemia, kosmetyki"));
            departmentEntityDAO.save(new DepartmentEntity("Karma dla zwierz¹t"));
            departmentEntityDAO.save(new DepartmentEntity("Kasy"));
            departmentEntityDAO.save(new DepartmentEntity("Kawa, herbata"));
            departmentEntityDAO.save(new DepartmentEntity("Konserwy, mro¿onki"));
            departmentEntityDAO.save(new DepartmentEntity("Magazyn"));
            departmentEntityDAO.save(new DepartmentEntity("Marka W³asna EC"));
            departmentEntityDAO.save(new DepartmentEntity("Miêso"));
            departmentEntityDAO.save(new DepartmentEntity("Nabia³"));
            departmentEntityDAO.save(new DepartmentEntity("Parking i wejœcie"));
            departmentEntityDAO.save(new DepartmentEntity("Pieczywo i ciasta"));
            departmentEntityDAO.save(new DepartmentEntity("Piwo i wody"));
            departmentEntityDAO.save(new DepartmentEntity("Promocje"));
            departmentEntityDAO.save(new DepartmentEntity("Przek¹ski"));
            departmentEntityDAO.save(new DepartmentEntity("Przemys³owe, zabawki"));
            departmentEntityDAO.save(new DepartmentEntity("Sa³atki, ryby"));
            departmentEntityDAO.save(new DepartmentEntity("Soki, napoje"));
            departmentEntityDAO.save(new DepartmentEntity("Sypkie i makarony"));
            departmentEntityDAO.save(new DepartmentEntity("S³odycze"));
            departmentEntityDAO.save(new DepartmentEntity("S³oiki"));
            departmentEntityDAO.save(new DepartmentEntity("Torepki"));
            departmentEntityDAO.save(new DepartmentEntity("Warzywa i owoce"));

        }catch (SQLException|DatabaseException ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> DAO<T> provideDAO(Class<T> clazz) throws DatabaseException {
        try {
            return DAO.create(clazz , DaoManager.createDao(connectionSource, clazz));
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
