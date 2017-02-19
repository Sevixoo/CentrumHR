package com.centrumhr.application.account;

import com.centrumhr.application.account.data.AccountCredentials;
import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.application.core.UseCase;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class CreateAccountUseCase extends UseCase<AccountCredentials,AccountData> {

    private IAccountService accountService;
    private com.centrumhr.application.account.ILoginService loginService;

    @Inject
    public CreateAccountUseCase(IAccountService accountService, com.centrumhr.application.account.ILoginService loginService) {
        this.accountService = accountService;
        this.loginService = loginService;
    }

    @Override
    public AccountData execute(AccountCredentials accountCredentials) throws DomainException {
        AccountData accountData = accountService.createAccount( accountCredentials );
        loginService.login(accountData);
        return accountData;
    }

}
