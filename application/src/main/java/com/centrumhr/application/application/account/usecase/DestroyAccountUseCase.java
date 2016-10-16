package com.centrumhr.application.application.account.usecase;

import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.ILoginService;
import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class DestroyAccountUseCase extends UseCase<Boolean> {

    private IAccountService mAccountService;
    private ILoginService   mLoginService;

    public DestroyAccountUseCase(IExecutor threadExecutor, ILoginService loginService, IHandler postExecutionThread, IAccountService mAccountService) {
        super(threadExecutor, postExecutionThread);
        this.mAccountService = mAccountService;
        this.mLoginService = loginService;
    }

    @Override
    public Boolean execute() throws Exception {
        mAccountService.destroyAccount();
        mLoginService.logout();
        return true;
    }
}
