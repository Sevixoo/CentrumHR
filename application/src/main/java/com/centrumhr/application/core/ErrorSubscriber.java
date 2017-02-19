package com.centrumhr.application.core;

/**
 * Created by Seweryn on 11.02.2017.
 */
public interface ErrorSubscriber {
    void onError(Throwable throwable);
}
