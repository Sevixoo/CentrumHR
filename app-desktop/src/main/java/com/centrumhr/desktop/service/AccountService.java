package com.centrumhr.desktop.service;

import com.centrumhr.application.account.IAccountService;
import com.centrumhr.application.account.data.AccountCredentials;
import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.application.account.exception.AccountServiceException;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class AccountService implements IAccountService{

    @Override
    public AccountData getLoggedAccount() throws AccountServiceException {
        try {
            Thread.sleep(1000);
        }catch (Exception ex){}
        return new AccountData();
    }

    @Override
    public AccountData createAccount(AccountCredentials accountCredentials) throws AccountServiceException {
        try {
            Thread.sleep(1000);
        }catch (Exception ex){}
        return new AccountData();
    }

    @Override
    public void destroyAccount() throws AccountServiceException {
        try {
            Thread.sleep(1000);
        }catch (Exception ex){}
    }
}
