package com.centrumhr.application.application.sync.usecase;

import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.common.IPostExecutionThread;
import com.centrumhr.application.application.common.IThreadExecutor;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.data.IDataBaseHelper;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class CreateDataBaseUseCase extends UseCase<IDataBaseHelper> {

    public AccountData      mAccountData;
    public IDataBaseService mCreateDataBase;

    public CreateDataBaseUseCase(IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread, IDataBaseService mCreateDataBase , AccountData mAccountData ) {
        super(threadExecutor, postExecutionThread);
        this.mAccountData = mAccountData;
        this.mCreateDataBase = mCreateDataBase;
    }

    @Override
    public IDataBaseHelper execute() throws Exception {
        return mCreateDataBase.createDataBase( mAccountData );
    }


}
