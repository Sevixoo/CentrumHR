package com.centrumhr.application.sync;

import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.application.core.UseCase;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class CreateDataBaseUseCase extends UseCase<AccountData,Boolean> {

    private IORMLiteDataBaseService mCreateDataBase;
    private IXMLDataBaseService xmlDataBaseService;

    @Inject
    public CreateDataBaseUseCase(IORMLiteDataBaseService mCreateDataBase, IXMLDataBaseService xmlDataBaseService) {
        this.mCreateDataBase = mCreateDataBase;
        this.xmlDataBaseService = xmlDataBaseService;
    }

    @Override
    public Boolean execute(AccountData accountData) throws DomainException {
        if(!xmlDataBaseService.dataBaseExists(accountData)){
            xmlDataBaseService.createDataBase( accountData );
        }
        if(!mCreateDataBase.dataBaseExists(accountData)){
            mCreateDataBase.createDataBase( accountData );
        }
        return null;
    }

}
