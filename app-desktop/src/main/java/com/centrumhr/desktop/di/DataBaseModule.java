package com.centrumhr.desktop.di;

import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.data.domain.*;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.importer.AttendancePlanImporter;
import com.centrumhr.data.importer.EmployeeImporter;
import com.centrumhr.data.orm.IORMLiteDataBase;
import com.centrumhr.data.orm.UnitOfWork;
import com.centrumhr.desktop.data.ORMLiteDatabase;
import com.centrumhr.desktop.data.RepositoryFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 18.09.2016.
 */
@Module
public class DataBaseModule {

    IDataBaseService mDataBaseService;

    public DataBaseModule(IDataBaseService dataBaseService){
        this.mDataBaseService = dataBaseService;
    }

    @Provides
    public UnitOfWork providesUnitOfWork(){
        return new UnitOfWork( mDataBaseService.provideDataBase() );
    }

    @Provides
    public RepositoryFactory provideRepositoryFactory(UnitOfWork unitOfWork ){
        return new RepositoryFactory( unitOfWork );
    }

    @Provides
    public IEmployeeRepository provideEmployeeRepository( RepositoryFactory repositoryFactory ){
        return repositoryFactory.createEmployeeRepository();
    }

    @Provides
    public IAttendancePlanRepository provideAttendancePlanRepository(RepositoryFactory repositoryFactory ){
        return repositoryFactory.createAttendancePlanRepository();
    }

    @Provides
    public IAttendanceDayRepository provideAttendanceDayRepository(RepositoryFactory repositoryFactory ){
        return repositoryFactory.createAttendanceDayRepository();
    }

    @Provides
    public IAttendanceEmployeeRepository provideAttendanceEmployeeRepository(RepositoryFactory repositoryFactory ){
        return repositoryFactory.createAttendanceEmployeeRepository();
    }

    @Provides
    public IDepartmentRepository provideDepartmentRepository( RepositoryFactory repositoryFactory ){
        return repositoryFactory.createDepartmentRepository();
    }

    @Provides
    public IWorkFunctionRepository provideWorkFunctionRepository(RepositoryFactory repositoryFactory ){
        return repositoryFactory.createWorkFunctionRepository();
    }

    @Provides
    public IEmployeeDepartmentRepository provideEmployeeDepartmentRepository(RepositoryFactory repositoryFactory ){
        return repositoryFactory.createEmployeeDepartmentRepository();
    }

    @Provides
    public EmployeeImporter privateEmployeeImporter(IDepartmentRepository mDepartmentRepository, IEmployeeRepository mEmployeeRepository, IWorkFunctionRepository mWorkFunctionRepository , IEmployeeDepartmentRepository employeeDepartmentRepository){
        return new EmployeeImporter( mDepartmentRepository , mEmployeeRepository , mWorkFunctionRepository , employeeDepartmentRepository);
    }

    @Provides
    public AttendancePlanImporter provideAttendancePlanImporter(IAttendanceEmployeeRepository mAttendanceEmployeeRepository, IAttendancePlanRepository mAttendancePlanRepository, IAttendanceDayRepository mAttendanceDayRepository){
        return new AttendancePlanImporter( mAttendanceEmployeeRepository , mAttendancePlanRepository , mAttendanceDayRepository );
    }

}
