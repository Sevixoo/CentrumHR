package com.centrumhr.application.application.account;

import com.centrumhr.application.application.account.data.AccountCredentials;
import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.account.exception.AccountServiceException;

/**
 * Created by Seweryn on 18.09.2016.
 */
public interface IAccountService {

    AccountData getLoggedAccount() throws AccountServiceException;

    AccountData createAccount( AccountCredentials accountCredentials ) throws AccountServiceException;

    void destroyAccount() throws AccountServiceException;

}
