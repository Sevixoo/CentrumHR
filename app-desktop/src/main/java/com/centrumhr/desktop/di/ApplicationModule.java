package com.centrumhr.desktop.di;

import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.ILoginService;
import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.desktop.service.AccountService;
import com.centrumhr.desktop.service.DataBaseService;
import com.centrumhr.desktop.service.LoginService;
import com.centrumhr.desktop.service.threading.HandlerThread;
import com.centrumhr.desktop.service.threading.ThreadExecutor;
import dagger.Module;
import dagger.Provides;
import javafx.application.Application;

import javax.inject.Singleton;

/**
 * Created by Seweryn on 18.09.2016.
 */
@Module
public class ApplicationModule{

    private Application mContext;

    private IHandler mPostExecutionThread;
    private IExecutor mThreadExecutor;

    public ApplicationModule(Application context) {
        this.mContext = context;
        System.out.println(Thread.currentThread().getName());
        mPostExecutionThread = HandlerThread.getInstance();
        mThreadExecutor = ThreadExecutor.getInstance();
    }

    @Provides
    public IAccountService provideAccountService() {
        return new AccountService();
    }

    @Provides
    public IHandler providePostExecutionThread() {
        return mPostExecutionThread;
    }

    @Provides
    public ILoginService provideLoginService() {
        return new LoginService();
    }

    @Provides
    public IExecutor provideThreadExecutor() {
        return mThreadExecutor;
    }

    @Provides @Singleton
    public IDataBaseService provideIDataBaseService() {
        return new DataBaseService();
    }

}
