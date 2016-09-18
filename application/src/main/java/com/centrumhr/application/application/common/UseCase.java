package com.centrumhr.application.application.common;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Seweryn on 18.09.2016.
 */

public abstract class UseCase<ResultType> {

    private final IThreadExecutor threadExecutor;
    private final IPostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();

    protected UseCase(IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * execute blocking
     */
    public abstract ResultType execute() throws Exception;

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current UseCase.
     */
    private Observable buildUseCaseObservable(){
        return Observable.create(new Observable.OnSubscribe<ResultType>() {
            @Override
            public void call(Subscriber<? super ResultType> subscriber) {
                long time = System.currentTimeMillis();
                try {
                    ResultType data = execute();
                    subscriber.onNext( data );
                    System.out.println( "[UseCase.call]" +  Thread.currentThread().getName() );
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                System.out.println( "[UseCase]"+ UseCase.this.getClass().getName() + " execution time:" + (System.currentTimeMillis() - time) + "ms" );
            }
        });
    };

    /**
     * Executes the current use case.
     *
     * @param UseCaseSubscriber The guy who will be listen to the observable build
     * with {@link #buildUseCaseObservable()}.
     */
    @SuppressWarnings("unchecked")
    public UseCase<ResultType> execute(Subscriber<ResultType> UseCaseSubscriber) {
        Observable observable = this.buildUseCaseObservable();
        observable.observeOn(Schedulers.from(threadExecutor));
        observable.subscribeOn(postExecutionThread.getScheduler());

        this.subscription =  observable.subscribe(UseCaseSubscriber);
        return this;
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
