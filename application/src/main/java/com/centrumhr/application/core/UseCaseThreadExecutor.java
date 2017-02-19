package com.centrumhr.application.core;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Seweryn on 11.02.2017.
 */
public class UseCaseThreadExecutor extends ThreadPoolExecutor {

    public static final UseCaseThreadExecutor INSTANCE = new UseCaseThreadExecutor();

    public UseCaseThreadExecutor() {
        super(3, 5, 3000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }
}
