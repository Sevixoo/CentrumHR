package com.centrumhr.desktop.service;

import com.centrumhr.application.account.ILoginService;
import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.desktop.CentrumHRApplication;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class LoginService implements ILoginService {

    @Override
    public void login(AccountData account) {
        CentrumHRApplication.getInstance().setLoggedAccount(account);
    }

    @Override
    public void logout() {
        CentrumHRApplication.getInstance().setLoggedAccount(null);
    }
}
