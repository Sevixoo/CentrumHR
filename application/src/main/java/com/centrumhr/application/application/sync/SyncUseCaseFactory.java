package com.centrumhr.application.application.sync;

import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.common.IPostExecutionThread;
import com.centrumhr.application.application.common.IThreadExecutor;
import com.centrumhr.application.application.sync.usecase.CreateDataBaseUseCase;
import com.centrumhr.application.application.sync.usecase.StartApplicationUseCase;
import com.centrumhr.application.application.sync.usecase.SyncDataBaseUseCase;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class SyncUseCaseFactory {

    private IThreadExecutor         mThreadExecutor;
    private IPostExecutionThread    mPostExecutionThread;
    private IDataBaseService        mDataBaseService;

    @Inject
    public SyncUseCaseFactory(IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread, IDataBaseService dataBaseService) {
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
