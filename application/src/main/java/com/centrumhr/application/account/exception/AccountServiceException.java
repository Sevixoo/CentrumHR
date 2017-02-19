package com.centrumhr.application.account.exception;

import com.centrumhr.domain.common.DomainException;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class AccountServiceException extends DomainException {
    public AccountServiceException(String message) {
        super(message);
    }
}
