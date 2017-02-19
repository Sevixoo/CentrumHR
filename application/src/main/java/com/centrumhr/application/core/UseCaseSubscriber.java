package com.centrumhr.application.core;

/**
 * Created by Seweryn on 11.02.2017.
 */
public interface UseCaseSubscriber<ResultObject> {
    void onResult( ResultObject resultObject );
}
