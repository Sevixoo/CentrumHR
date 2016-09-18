package com.centrumhr.desktop.service;

import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.data.AccountCredentials;
import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.account.exception.AccountServiceException;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class AccountService implements IAccountService{

    @Override
    public AccountData getLoggedAccount() throws AccountServiceException {
        return null;
    }

    @Override
    public AccountData createAccount(AccountCredentials accountCredentials) throws AccountServiceException {
        return null;
    }

    @Override
    public void destroyAccount() throws AccountServiceException {

    }
}
