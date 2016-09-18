package com.centrumhr.application.application.account.exception;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class AccountNotExists extends AccountServiceException {
    public AccountNotExists() {
        super("Account don't exists");
    }
}
