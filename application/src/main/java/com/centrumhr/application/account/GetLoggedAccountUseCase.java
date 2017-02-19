package com.centrumhr.application.account;

import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.application.account.exception.AccountNotExists;
import com.centrumhr.application.core.UseCase;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class GetLoggedAccountUseCase extends UseCase<Boolean,AccountData> {

    private IAccountService accountService;
    private ILoginService loginService;

    @Inject
    public GetLoggedAccountUseCase(IAccountService accountService, ILoginService loginService) {
        this.accountService = accountService;
        this.loginService = loginService;
    }

    @Override
    public AccountData execute(Boolean arg) throws DomainException {
        AccountData accountData = accountService.getLoggedAccount();
        if(accountData == null){
            throw new AccountNotExists();
        }
        loginService.login( accountData );
        return accountData;
    }

}
