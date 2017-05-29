package com.centrumhr.application.sync;

import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.core.xml.IXMLDataBase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public interface IXMLDataBaseService {

    String getDatabaseName(AccountData accountData);

    boolean dataBaseExists(AccountData accountData);

    IXMLDataBase createDataBase(AccountData accountData);

    IXMLDataBase provideDataBase();
}
