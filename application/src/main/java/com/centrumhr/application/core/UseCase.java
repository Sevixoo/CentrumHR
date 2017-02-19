package com.centrumhr.application.core;

import com.centrumhr.domain.common.DomainException;

public abstract class UseCase<RequestObject,ResultObject> {

    public abstract ResultObject execute( RequestObject requestObject )throws DomainException;

}
