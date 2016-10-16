package com.centrumhr.application.application.sync.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class StartApplicationUseCase extends UseCase<Boolean> {

    public StartApplicationUseCase(IExecutor threadExecutor, IHandler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    public Boolean execute() throws Exception {
        Thread.sleep(1000);
        return true;
    }
}
