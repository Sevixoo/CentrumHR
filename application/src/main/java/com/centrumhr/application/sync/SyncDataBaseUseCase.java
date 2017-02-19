package com.centrumhr.application.sync;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.domain.common.DomainException;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class SyncDataBaseUseCase extends UseCase<Boolean,Boolean> {

    @Inject
    public SyncDataBaseUseCase() { }

    @Override
    public Boolean execute(Boolean aBoolean) throws DomainException {
        try {
            Thread.sleep(2000);
        }catch (Exception ex){}
        return true;
    }

}
