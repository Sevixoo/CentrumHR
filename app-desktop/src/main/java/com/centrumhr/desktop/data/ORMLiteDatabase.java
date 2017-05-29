package com.centrumhr.desktop.data;

import com.centrumhr.data.core.ormlite.DAO;
import com.centrumhr.data.core.DatabaseException;
import com.centrumhr.data.model.attendance.AttendanceDayEntity;
import com.centrumhr.data.model.attendance.AttendanceEmployeeEntity;
import com.centrumhr.data.model.attendance.AttendancePlanEntity;
import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.data.model.employment.EmployeeEntity;
import com.centrumhr.data.model.employment.EmployeeDepartmentEntity;
import com.centrumhr.data.model.employment.WorkFunctionEntity;
import com.centrumhr.data.model.settings.SettingItem;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.dto.employment.WorkTime;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

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

            WorkFunctionEntity functionEntity1 = new WorkFunctionEntity("sprzedawca-kasjer 2");
            WorkFunctionEntity functionEntity2 = new WorkFunctionEntity("dzia³ piekarniczy");

            workFunctionDAO.save(functionEntity1);
            workFunctionDAO.save(functionEntity2);

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

            DepartmentEntity departmentEntity1 = new DepartmentEntity("S³oiki");
            DepartmentEntity departmentEntity2 = new DepartmentEntity("Torepki");
            DepartmentEntity departmentEntity3 = new DepartmentEntity("Warzywa i owoce");

            departmentEntityDAO.save(departmentEntity1);
            departmentEntityDAO.save(departmentEntity2);
            departmentEntityDAO.save(departmentEntity3);

            DAO<EmployeeEntity> employeeEntityDAO = provideDAO(EmployeeEntity.class);

            Collection<EmployeeDepartmentEntity> employeeDepartmentEntities1 = new ArrayList<>();
            Collection<EmployeeDepartmentEntity> employeeDepartmentEntities2 = new ArrayList<>();

            EmployeeEntity employeeEntity1 = new EmployeeEntity(
                    UUID.randomUUID(),
                    "John",
                    "Rumbo",
                    "johrum",
                    WorkTime.FULL_TIME,
                    true,
                    new Date(),
                    functionEntity1,
                    employeeDepartmentEntities1
            );

            EmployeeEntity employeeEntity2 = new EmployeeEntity(
                    UUID.randomUUID(),
                    "Rocky",
                    "Balbao",
                    "Rocbal",
                    WorkTime.FULL_TIME,
                    true,
                    new Date(),
                    functionEntity2,
                    employeeDepartmentEntities2
            );

            DAO<EmployeeDepartmentEntity> employeeDepartmentEntityDAO = provideDAO(EmployeeDepartmentEntity.class);

            EmployeeDepartmentEntity employeeDepartmentEntity1 = new EmployeeDepartmentEntity( employeeEntity1 , departmentEntity1 );
            EmployeeDepartmentEntity employeeDepartmentEntity2 = new EmployeeDepartmentEntity( employeeEntity1 , departmentEntity2 );

            employeeEntity1.getDepartments().add(employeeDepartmentEntity1);
            employeeEntity1.getDepartments().add(employeeDepartmentEntity2);

            EmployeeDepartmentEntity employeeDepartmentEntity3 = new EmployeeDepartmentEntity( employeeEntity2 , departmentEntity1 );
            EmployeeDepartmentEntity employeeDepartmentEntity4 = new EmployeeDepartmentEntity( employeeEntity2 , departmentEntity3 );

            employeeEntity2.getDepartments().add(employeeDepartmentEntity3);
            employeeEntity2.getDepartments().add(employeeDepartmentEntity4);

            employeeDepartmentEntityDAO.save(employeeDepartmentEntity1);
            employeeDepartmentEntityDAO.save(employeeDepartmentEntity2);
            employeeDepartmentEntityDAO.save(employeeDepartmentEntity3);
            employeeDepartmentEntityDAO.save(employeeDepartmentEntity4);

            employeeEntityDAO.save(employeeEntity1);
            employeeEntityDAO.save(employeeEntity2);

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
