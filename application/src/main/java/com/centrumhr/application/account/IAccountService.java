package com.centrumhr.application.account;

import com.centrumhr.application.account.data.AccountCredentials;
import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.application.account.exception.AccountServiceException;

/**
 * Created by Seweryn on 18.09.2016.
 */
public interface IAccountService {

    AccountData getLoggedAccount() throws AccountServiceException;

    AccountData createAccount( AccountCredentials accountCredentials ) throws com.centrumhr.application.account.exception.AccountServiceException;

    void destroyAccount() throws com.centrumhr.application.account.exception.AccountServiceException;

}
