package com.centrumhr.desktop.service.threading;

import com.centrumhr.application.application.common.IExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutor implements IExecutor {

    // This is a singleton
    private static volatile ThreadExecutor sThreadExecutor;

    private static final int                     CORE_POOL_SIZE  = 3;
    private static final int                     MAX_POOL_SIZE   = 5;
    private static final int                     KEEP_ALIVE_TIME = 120;
    private static final TimeUnit                TIME_UNIT       = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE      = new LinkedBlockingQueue<>();

    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadExecutor() {
        long keepAlive = KEEP_ALIVE_TIME;
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE);
    }

    @Override
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.submit(runnable);
    }

    /**
     * Returns a singleton instance of this executor. If the executor is not initialized then it initializes it and returns
     * the instance.
     */
    public static IExecutor getInstance() {
        if (sThreadExecutor == null) {
            sThreadExecutor = new ThreadExecutor();
        }

        return sThreadExecutor;
    }
}