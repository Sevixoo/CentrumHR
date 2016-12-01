package com.centrumhr.application.application.workFunction;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.workFunction.usecase.LoadWorkFunctionsUseCase;
import com.centrumhr.data.domain.IWorkFunctionRepository;

import javax.inject.Inject;

/**
 * Created by Seweryn on 25.10.2016.
 */
public class WorkFunctionUseCaseFactory {

    private IExecutor executor;
    private IHandler handler;
    private IWorkFunctionRepository mWorkFunctionRepository;

    @Inject
    public WorkFunctionUseCaseFactory(IExecutor executor, IHandler handler, IWorkFunctionRepository mWorkFunctionRepository) {
        this.executor = executor;
        this.handler = handler;
        this.mWorkFunctionRepository = mWorkFunctionRepository;
    }

    public LoadWorkFunctionsUseCase createLoadWorkFunctionsUseCase(){
        return new LoadWorkFunctionsUseCase( executor , handler , mWorkFunctionRepository );
    }

}
