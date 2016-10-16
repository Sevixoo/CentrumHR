package com.centrumhr.application.application.sync.usecase;

import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.data.orm.IORMLiteDataBase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class CreateDataBaseUseCase extends UseCase<IORMLiteDataBase> {

    private AccountData      mAccountData;
    private IDataBaseService mCreateDataBase;

    public CreateDataBaseUseCase(IExecutor threadExecutor, IHandler  postExecutionThread, IDataBaseService mCreateDataBase , AccountData mAccountData ) {
        super(threadExecutor, postExecutionThread);
        this.mAccountData = mAccountData;
        this.mCreateDataBase = mCreateDataBase;
    }

    @Override
    public IORMLiteDataBase execute() throws Exception {
        return mCreateDataBase.createDataBase( mAccountData );
    }


}
