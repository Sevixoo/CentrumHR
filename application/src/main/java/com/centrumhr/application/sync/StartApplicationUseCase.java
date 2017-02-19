package com.centrumhr.application.sync;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class StartApplicationUseCase extends UseCase<Boolean,Boolean> {

    @Inject
    public StartApplicationUseCase() { }

    @Override
    public Boolean execute(Boolean aBoolean) throws DomainException {
        try {
            Thread.sleep(1000);
        }catch (Exception ex){}
        return true;
    }

}
