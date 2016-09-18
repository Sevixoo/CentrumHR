package com.centrumhr.application.application.sync;

import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.data.IDataBaseHelper;

/**
 * Created by Seweryn on 18.09.2016.
 */
public interface IDataBaseService {

    boolean dataBaseExists();

    IDataBaseHelper createDataBase(AccountData accountData);

}
