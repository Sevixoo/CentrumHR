package com.centrumhr.application.sync;

import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public interface IORMLiteDataBaseService {

    String getDatabaseName( AccountData accountData );

    boolean dataBaseExists( AccountData accountData );

    IORMLiteDataBase createDataBase(AccountData accountData);

    IORMLiteDataBase provideDataBase();
}
