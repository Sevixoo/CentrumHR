package com.centrumhr.application.application.account.usecase;

import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.ILoginService;
import com.centrumhr.application.application.account.data.AccountCredentials;
import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class CreateAccountUseCase extends UseCase<AccountData> {

    private IAccountService         mAccountService;
    private ILoginService           mLoginService;
    private AccountCredentials      mAccountCredentials;

    public CreateAccountUseCase(IExecutor threadExecutor, IHandler postExecutionThread, IAccountService mAccountService, AccountCredentials mAccountCredentials , ILoginService loginService) {
        super(threadExecutor, postExecutionThread);
        this.mAccountService = mAccountService;
        this.mAccountCredentials = mAccountCredentials;
        this.mLoginService = loginService;
    }

    @Override
    public AccountData execute() throws Exception {
        AccountData accountData = mAccountService.createAccount( mAccountCredentials );
        mLoginService.login(accountData);
        return accountData;
    }
}
