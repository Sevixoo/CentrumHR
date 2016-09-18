package com.centrumhr.desktop.di;

import com.centrumhr.application.application.account.AccountUseCaseFactory;
import com.centrumhr.application.application.account.AccountUseCaseFactory_Factory;
import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.ILoginService;
import com.centrumhr.application.application.common.IPostExecutionThread;
import com.centrumhr.application.application.common.IThreadExecutor;
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.application.application.sync.SyncUseCaseFactory;
import com.centrumhr.application.application.sync.SyncUseCaseFactory_Factory;
import com.centrumhr.application.presenter.SplashPresenter;
import com.centrumhr.application.presenter.SplashPresenter_Factory;
import com.centrumhr.desktop.ui.SplashController;
import com.centrumhr.desktop.ui.SplashController_MembersInjector;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerAppComponent implements AppComponent {
  private Provider<IAccountService> provideAccountServiceProvider;
  private Provider<ILoginService> provideLoginServiceProvider;
  private Provider<IThreadExecutor> provideThreadExecutorProvider;
  private Provider<IPostExecutionThread> providePostExecutionThreadProvider;
  private Provider<AccountUseCaseFactory> accountUseCaseFactoryProvider;
  private Provider<IDataBaseService> provideIDataBaseServiceProvider;
  private Provider<SyncUseCaseFactory> syncUseCaseFactoryProvider;
  private Provider<SplashPresenter> splashPresenterProvider;
  private MembersInjector<SplashController> splashControllerMembersInjector;

  private DaggerAppComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideAccountServiceProvider = ApplicationModule_ProvideAccountServiceFactory.create(builder.applicationModule);
    this.provideLoginServiceProvider = ApplicationModule_ProvideLoginServiceFactory.create(builder.applicationModule);
    this.provideThreadExecutorProvider = ApplicationModule_ProvideThreadExecutorFactory.create(builder.applicationModule);
    this.providePostExecutionThreadProvider = ApplicationModule_ProvidePostExecutionThreadFactory.create(builder.applicationModule);
    this.accountUseCaseFactoryProvider = AccountUseCaseFactory_Factory.create(provideAccountServiceProvider, provideLoginServiceProvider, provideThreadExecutorProvider, providePostExecutionThreadProvider);
    this.provideIDataBaseServiceProvider = ApplicationModule_ProvideIDataBaseServiceFactory.create(builder.applicationModule);
    this.syncUseCaseFactoryProvider = SyncUseCaseFactory_Factory.create(provideThreadExecutorProvider, providePostExecutionThreadProvider, provideIDataBaseServiceProvider);
    this.splashPresenterProvider = SplashPresenter_Factory.create(accountUseCaseFactoryProvider, syncUseCaseFactoryProvider, provideIDataBaseServiceProvider);
    this.splashControllerMembersInjector = SplashController_MembersInjector.create(splashPresenterProvider);
  }

  @Override
  public void inject(SplashController controller) {  
    splashControllerMembersInjector.injectMembers(controller);
  }

  @Override
  public SplashPresenter getSplashPresenter() {  
    return splashPresenterProvider.get();
  }

  public static final class Builder {
    private ApplicationModule applicationModule;
  
    private Builder() {  
    }
  
    public AppComponent build() {  
      if (applicationModule == null) {
        throw new IllegalStateException("applicationModule must be set");
      }
      return new DaggerAppComponent(this);
    }
  
    public Builder applicationModule(ApplicationModule applicationModule) {  
      if (applicationModule == null) {
        throw new NullPointerException("applicationModule");
      }
      this.applicationModule = applicationModule;
      return this;
    }
  }
}

