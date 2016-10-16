package com.centrumhr.desktop.di;

import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.exception.DatabaseException;
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

}
