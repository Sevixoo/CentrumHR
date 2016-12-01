package com.centrumhr.application.application.workFunction.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IWorkFunctionRepository;
import com.centrumhr.data.model.employment.WorkFunction;

import java.util.List;

/**
 * Created by Seweryn on 25.10.2016.
 */
public class LoadWorkFunctionsUseCase extends UseCase<List<WorkFunction>> {

    private IWorkFunctionRepository mWorkFunctionRepository;

    public LoadWorkFunctionsUseCase(IExecutor executor, IHandler handler, IWorkFunctionRepository mWorkFunctionRepository) {
        super(executor, handler);
        this.mWorkFunctionRepository = mWorkFunctionRepository;
    }

    @Override
    public List<WorkFunction> execute() throws Exception {
        return mWorkFunctionRepository.list();
    }
}
