package com.centrumhr.application.application.sync;

import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.orm.IORMLiteDataBase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public interface IDataBaseService {

    String getDatabaseName( AccountData accountData );

    boolean dataBaseExists( AccountData accountData );

    IORMLiteDataBase createDataBase(AccountData accountData);

    IORMLiteDataBase provideDataBase();
}
