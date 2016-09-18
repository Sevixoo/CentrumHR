package com.centrumhr.application.application.sync.usecase;

import com.centrumhr.application.application.common.IPostExecutionThread;
import com.centrumhr.application.application.common.IThreadExecutor;
import com.centrumhr.application.application.common.UseCase;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class SyncDataBaseUseCase extends UseCase<Boolean> {

    public SyncDataBaseUseCase(IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    public Boolean execute() throws Exception {
        Thread.sleep(2000);
        return true;
    }
}
