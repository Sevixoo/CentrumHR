package com.centrumhr.desktop.threading;

import com.centrumhr.application.application.common.IPostExecutionThread;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class UIThread implements IPostExecutionThread {


    public UIThread() {

    }

    @Override public Scheduler getScheduler() {
        System.out.println("getScheduler"+Thread.currentThread().getName());
        return Schedulers.immediate();
    }
}
