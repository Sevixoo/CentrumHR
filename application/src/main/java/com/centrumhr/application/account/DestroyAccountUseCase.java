package com.centrumhr.application.account;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class DestroyAccountUseCase extends UseCase<Boolean,Boolean> {

    private IAccountService accountService;
    private ILoginService   loginService;

    @Inject
    public DestroyAccountUseCase(IAccountService accountService, ILoginService loginService) {
        this.accountService = accountService;
        this.loginService = loginService;
    }

    @Override
    public Boolean execute(Boolean aBoolean) throws DomainException {
        accountService.destroyAccount();
        loginService.logout();
        return true;
    }

}
