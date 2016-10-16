package com.centrumhr.application.application.sync;

import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.sync.usecase.CreateDataBaseUseCase;
import com.centrumhr.application.application.sync.usecase.StartApplicationUseCase;
import com.centrumhr.application.application.sync.usecase.SyncDataBaseUseCase;

import javax.inject.Inject;

public class SyncUseCaseFactory {

    private IExecutor               mThreadExecutor;
    private IHandler                mPostExecutionThread;
    private IDataBaseService        mDataBaseService;

    @Inject
    public SyncUseCaseFactory(IExecutor threadExecutor, IHandler postExecutionThread, IDataBaseService dataBaseService) {
        this.mThreadExecutor = threadExecutor;
        this.mPostExecutionThread = postExecutionThread;
        this.mDataBaseService = dataBaseService;
    }

    public CreateDataBaseUseCase createCreateDataBaseUseCase(AccountData accountData ){
        return new CreateDataBaseUseCase( mThreadExecutor , mPostExecutionThread , mDataBaseService , accountData );
    }

    public StartApplicationUseCase createStartApplicationUseCase(){
        return new StartApplicationUseCase( mThreadExecutor , mPostExecutionThread );
    }

    public SyncDataBaseUseCase createSyncDataBaseUseCase(){
        return new SyncDataBaseUseCase( mThreadExecutor , mPostExecutionThread );
    }

}
