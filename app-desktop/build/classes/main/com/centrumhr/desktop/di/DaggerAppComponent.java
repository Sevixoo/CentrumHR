package com.centrumhr.desktop.di;

import com.centrumhr.application.application.account.AccountUseCaseFactory;
import com.centrumhr.application.application.account.AccountUseCaseFactory_Factory;
import com.centrumhr.application.application.account.IAccountService;
import com.centrumhr.application.application.account.ILoginService;
import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.employee.EmployeeUseCaseFactory;
import com.centrumhr.application.application.employee.EmployeeUseCaseFactory_Factory;
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.application.application.sync.SyncUseCaseFactory;
import com.centrumhr.application.application.sync.SyncUseCaseFactory_Factory;
import com.centrumhr.application.presenter.AddEmployeePresenter;
import com.centrumhr.application.presenter.AddEmployeePresenter_Factory;
import com.centrumhr.application.presenter.EmployeeListPresenter;
import com.centrumhr.application.presenter.EmployeeListPresenter_Factory;
import com.centrumhr.application.presenter.SplashPresenter;
import com.centrumhr.application.presenter.SplashPresenter_Factory;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.orm.UnitOfWork;
import com.centrumhr.desktop.data.RepositoryFactory;
import com.centrumhr.desktop.ui.employee.AddEmployeeController;
import com.centrumhr.desktop.ui.employee.AddEmployeeController_MembersInjector;
import com.centrumhr.desktop.ui.employee.EmployeeListController;
import com.centrumhr.desktop.ui.employee.EmployeeListController_MembersInjector;
import com.centrumhr.desktop.ui.start.SplashController;
import com.centrumhr.desktop.ui.start.SplashController_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerAppComponent implements AppComponent {
  private Provider<IAccountService> provideAccountServiceProvider;
  private Provider<ILoginService> provideLoginServiceProvider;
  private Provider<IExecutor> provideThreadExecutorProvider;
  private Provider<IHandler> providePostExecutionThreadProvider;
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
    this.provideIDataBaseServiceProvider = ScopedProvider.create(ApplicationModule_ProvideIDataBaseServiceFactory.create(builder.applicationModule));
    this.syncUseCaseFactoryProvider = SyncUseCaseFactory_Factory.create(provideThreadExecutorProvider, providePostExecutionThreadProvider, provideIDataBaseServiceProvider);
    this.splashPresenterProvider = SplashPresenter_Factory.create(accountUseCaseFactoryProvider, syncUseCaseFactoryProvider, provideIDataBaseServiceProvider);
    this.splashControllerMembersInjector = SplashController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), splashPresenterProvider);
  }

  @Override
  public void inject(SplashController controller) {  
    splashControllerMembersInjector.injectMembers(controller);
  }

  @Override
  public SplashPresenter getSplashPresenter() {  
    return splashPresenterProvider.get();
  }

  @Override
  public IDataBaseService getDataBaseService() {  
    return provideIDataBaseServiceProvider.get();
  }

  @Override
  public LoggedAccountComponent getLoggedAccountComponent(DataBaseModule dataBaseModule, AccountModule accountModule) {  
    return new LoggedAccountComponentImpl(dataBaseModule, accountModule);
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

  private final class LoggedAccountComponentImpl implements LoggedAccountComponent {
    private final DataBaseModule dataBaseModule;
    private final AccountModule accountModule;
    private Provider<UnitOfWork> providesUnitOfWorkProvider;
    private Provider<RepositoryFactory> provideRepositoryFactoryProvider;
    private Provider<IEmployeeRepository> provideEmployeeRepositoryProvider;
    private Provider<EmployeeUseCaseFactory> employeeUseCaseFactoryProvider;
    private Provider<AddEmployeePresenter> addEmployeePresenterProvider;
    private MembersInjector<AddEmployeeController> addEmployeeControllerMembersInjector;
    private Provider<EmployeeListPresenter> employeeListPresenterProvider;
    private MembersInjector<EmployeeListController> employeeListControllerMembersInjector;
    private MembersInjector<SplashController> splashControllerMembersInjector;
  
    private LoggedAccountComponentImpl(DataBaseModule dataBaseModule, AccountModule accountModule) {  
      if (dataBaseModule == null) {
        throw new NullPointerException();
      }
      this.dataBaseModule = dataBaseModule;
      if (accountModule == null) {
        throw new NullPointerException();
      }
      this.accountModule = accountModule;
      initialize();
    }
  
    private void initialize() {  
      this.providesUnitOfWorkProvider = DataBaseModule_ProvidesUnitOfWorkFactory.create(dataBaseModule);
      this.provideRepositoryFactoryProvider = DataBaseModule_ProvideRepositoryFactoryFactory.create(dataBaseModule, providesUnitOfWorkProvider);
      this.provideEmployeeRepositoryProvider = DataBaseModule_ProvideEmployeeRepositoryFactory.create(dataBaseModule, provideRepositoryFactoryProvider);
      this.employeeUseCaseFactoryProvider = EmployeeUseCaseFactory_Factory.create(DaggerAppComponent.this.provideThreadExecutorProvider, DaggerAppComponent.this.providePostExecutionThreadProvider, provideEmployeeRepositoryProvider);
      this.addEmployeePresenterProvider = AddEmployeePresenter_Factory.create(employeeUseCaseFactoryProvider);
      this.addEmployeeControllerMembersInjector = AddEmployeeController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), addEmployeePresenterProvider);
      this.employeeListPresenterProvider = EmployeeListPresenter_Factory.create(employeeUseCaseFactoryProvider);
      this.employeeListControllerMembersInjector = EmployeeListController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), employeeListPresenterProvider);
      this.splashControllerMembersInjector = SplashController_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), DaggerAppComponent.this.splashPresenterProvider);
    }
  
    @Override
    public void inject(AddEmployeeController controller) {  
      addEmployeeControllerMembersInjector.injectMembers(controller);
    }
  
    @Override
    public void inject(EmployeeListController controller) {  
      employeeListControllerMembersInjector.injectMembers(controller);
    }
  
    @Override
    public AddEmployeePresenter getAddEmployeePresenter() {  
      return addEmployeePresenterProvider.get();
    }
  
    @Override
    public EmployeeListPresenter getEmployeeListPresenter() {  
      return employeeListPresenterProvider.get();
    }
  
    @Override
    public EmployeeUseCaseFactory getEmployeeUseCaseFactory() {  
      return employeeUseCaseFactoryProvider.get();
    }
  }
}

