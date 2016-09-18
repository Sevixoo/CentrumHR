package com.centrumhr.desktop.service;

import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.data.IDataBaseHelper;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class DataBaseService implements IDataBaseService {

    @Override
    public boolean dataBaseExists() {
        return false;
    }

    @Override
    public IDataBaseHelper createDataBase(AccountData accountData) {
        return null;
    }
}
