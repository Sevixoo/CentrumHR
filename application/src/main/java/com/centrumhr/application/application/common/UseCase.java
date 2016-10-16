package com.centrumhr.application.application.common;

public abstract class UseCase<ResultType> {

    private final IExecutor executor;
    private final IHandler handler;

    private boolean isSubscribed = false;

    protected UseCase(IExecutor executor, IHandler handler) {
        this.executor = executor;
        this.handler = handler;
    }

    public abstract ResultType execute() throws Exception;

    public UseCase<ResultType> execute(UseCaseCallback<ResultType> useCaseSubscriber) {
        isSubscribed = true;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                try {
                    ResultType data = execute();
                    if(isSubscribed) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                useCaseSubscriber.onResult(data);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(isSubscribed) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                useCaseSubscriber.onError(e);
                            }
                        });
                    }
                }
                System.out.println( "[UseCase]"+ UseCase.this.getClass().getName() + " execution time:" + (System.currentTimeMillis() - time) + "ms" );
            }
        });
        return this;
    }

    public void unsubscribe() {
        isSubscribed = false;
    }
}
