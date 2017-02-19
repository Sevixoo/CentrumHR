package com.centrumhr.application.sync;

import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class CreateDataBaseUseCase extends UseCase<AccountData,IORMLiteDataBase> {

    private IDataBaseService mCreateDataBase;

    @Inject
    public CreateDataBaseUseCase(IDataBaseService mCreateDataBase) {
        this.mCreateDataBase = mCreateDataBase;
    }

    @Override
    public IORMLiteDataBase execute(AccountData accountData) throws DomainException {
        return mCreateDataBase.createDataBase( accountData );
    }

}
