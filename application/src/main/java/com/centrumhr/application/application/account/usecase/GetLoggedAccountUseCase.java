package com.centrumhr.application.application.account.usecase;

import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.ILoginService;
import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.common.IPostExecutionThread;
import com.centrumhr.application.application.common.IThreadExecutor;
import com.centrumhr.application.application.common.UseCase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class GetLoggedAccountUseCase extends UseCase<AccountData> {

    private IAccountService mAccountService;
    private ILoginService   mLoginService;

    public GetLoggedAccountUseCase(IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread, IAccountService accountService , ILoginService loginService) {
        super(threadExecutor, postExecutionThread);
        this.mAccountService = accountService;
        this.mLoginService = loginService;
    }

    @Override
    public AccountData execute() throws Exception {
        AccountData accountData = mAccountService.getLoggedAccount();
        mLoginService.login( accountData );
        return accountData;
    }
}
