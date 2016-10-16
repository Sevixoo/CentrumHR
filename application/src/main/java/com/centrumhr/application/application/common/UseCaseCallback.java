package com.centrumhr.application.application.common;

public abstract class UseCaseCallback<T>{

    public abstract void onResult(T data);

    public abstract void onError(Throwable ex);

}
