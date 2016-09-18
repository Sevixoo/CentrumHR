package com.centrumhr.application.application.common;

import rx.Subscriber;

/**
 * Created by Seweryn on 18.09.2016.
 */
public abstract class UseCaseSubscriber<T> extends Subscriber<T> {
    @Override
    public final void onCompleted() { }

    @Override
    public final void onNext(T data) {
        onResult(data);
    }
    public abstract void onResult(T data);

}
