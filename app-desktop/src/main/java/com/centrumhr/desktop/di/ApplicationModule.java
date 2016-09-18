package com.centrumhr.desktop.di;

import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.ILoginService;
import com.centrumhr.application.application.common.IPostExecutionThread;
import com.centrumhr.application.application.common.IThreadExecutor;
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.data.IDataBaseHelper;
import com.centrumhr.desktop.data.DataBaseHelper;
import com.centrumhr.desktop.service.AccountService;
import com.centrumhr.desktop.service.DataBaseService;
import com.centrumhr.desktop.service.LoginService;
import com.centrumhr.desktop.threading.AsyncExecutor;
import com.centrumhr.desktop.threading.UIThread;
import dagger.Module;
import dagger.Provides;
import javafx.application.Application;

/**
 * Created by Seweryn on 18.09.2016.
 */
@Module
public class ApplicationModule{

    private Application mContext;

    private IPostExecutionThread mPostExecutionThread;
    private IThreadExecutor mThreadExecutor;

    public ApplicationModule(Application context) {
        this.mContext = context;
        System.out.println(Thread.currentThread().getName());
        mPostExecutionThread = new UIThread();
        mThreadExecutor = new AsyncExecutor();
    }

    @Provides
    public IAccountService provideAccountService() {
        return new AccountService();
    }

    @Provides
    public IPostExecutionThread providePostExecutionThread() {
        return mPostExecutionThread;
    }

    @Provides
    public ILoginService provideLoginService() {
        return new LoginService();
    }

    @Provides
    public IThreadExecutor provideThreadExecutor() {
        return mThreadExecutor;
    }

    @Provides
    public IDataBaseService provideIDataBaseService() {
        return new DataBaseService();
    }

    @Provides
    public IDataBaseHelper provideDataBaseHelper() {
        return new DataBaseHelper();
    }
}
