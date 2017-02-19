package com.centrumhr.application.core;


import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Seweryn on 11.02.2017.
 */
public class Presenter<View extends UI> {

    protected View view;
    protected ThreadPoolExecutor executor;
    private Subscription pendingSubscription;
    private Scheduler scheduler;

    public Presenter(Scheduler scheduler , ThreadPoolExecutor threadPoolExecutor) {
        this.scheduler = scheduler;
        this.executor = threadPoolExecutor;
    }

    public void setView(View view){
        this.view = view;
    }

    public void onDestroy(){
        this.view = null;
        if(pendingSubscription!=null){
            pendingSubscription.unsubscribe();
        }
    }


    private <RequestObject,ResultObject> Observable createUseCaseObservable(final UseCase<RequestObject,ResultObject> useCase , final RequestObject argument){
        return Observable.create( subscriber -> {
            long time = System.currentTimeMillis();
            try {
                ResultObject result = useCase.execute(argument);
                subscriber.onNext(result);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
            System.out.println( "[UseCase]"+ useCase.getClass().getName() + " execution time:" + (System.currentTimeMillis() - time) + "ms" );
        })
        .subscribeOn(Schedulers.from(executor))
        .observeOn(scheduler);
    }

    public <RequestObject,ResultObject> void executeUseCase(UseCase<RequestObject,ResultObject> useCase , UseCaseSubscriber<ResultObject> onComplete , ErrorSubscriber onError) {
        executeUseCase(useCase,null,onComplete,onError);
    }

        @SuppressWarnings("unchecked")
    public <RequestObject,ResultObject> void executeUseCase(UseCase<RequestObject,ResultObject> useCase , RequestObject argument , UseCaseSubscriber<ResultObject> onComplete , ErrorSubscriber onError){
        Observable observable = createUseCaseObservable(useCase,argument);
        pendingSubscription = observable.subscribe(new Subscriber<ResultObject>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) {
                onError.onError(e);
            }

            @Override
            public void onNext(ResultObject resultObject) {
                onComplete.onResult(resultObject);
            }
        });
    }

}
