package com.centrumhr.application.application.account.usecase;

import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.ILoginService;
import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.account.exception.AccountNotExists;
import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class GetLoggedAccountUseCase extends UseCase<AccountData> {

    private IAccountService mAccountService;
    private ILoginService   mLoginService;

    public GetLoggedAccountUseCase(IExecutor threadExecutor, IHandler postExecutionThread, IAccountService accountService , ILoginService loginService) {
        super(threadExecutor, postExecutionThread);
        this.mAccountService = accountService;
        this.mLoginService = loginService;
    }

    @Override
    public AccountData execute() throws Exception {
        AccountData accountData = mAccountService.getLoggedAccount();
        if(accountData == null){
            throw new AccountNotExists();
        }
        mLoginService.login( accountData );
        return accountData;
    }
}
