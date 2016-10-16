package com.centrumhr.application.application.account;

import com.centrumhr.application.application.account.data.AccountCredentials;
import com.centrumhr.application.application.account.usecase.CreateAccountUseCase;
import com.centrumhr.application.application.account.usecase.DestroyAccountUseCase;
import com.centrumhr.application.application.account.usecase.GetLoggedAccountUseCase;
import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;

import javax.inject.Inject;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class AccountUseCaseFactory {

    private IAccountService         mAccountService;
    private ILoginService           mLoginService;
    private IExecutor               mThreadExecutor;
    private IHandler                mPostExecutionThread;

    @Inject
    public AccountUseCaseFactory(IAccountService mAccountService,ILoginService loginService, IExecutor mThreadExecutor, IHandler mPostExecutionThread) {
        this.mAccountService = mAccountService;
        this.mThreadExecutor = mThreadExecutor;
        this.mPostExecutionThread = mPostExecutionThread;
        this.mLoginService = loginService;
    }

    public GetLoggedAccountUseCase createGetLoggedAccountUseCase(){
        return new GetLoggedAccountUseCase( mThreadExecutor , mPostExecutionThread , mAccountService , mLoginService);
    }

    public DestroyAccountUseCase createDestroyAccountUseCase(){
        return new DestroyAccountUseCase(  mThreadExecutor ,  mLoginService, mPostExecutionThread ,mAccountService   );
    }

    public CreateAccountUseCase createCreateAccountUseCase(AccountCredentials accountCredentials){
        return new CreateAccountUseCase( mThreadExecutor , mPostExecutionThread , mAccountService  , accountCredentials , mLoginService );
    }

}
