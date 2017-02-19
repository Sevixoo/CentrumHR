package com.centrumhr.desktop.ui.start;

import com.centrumhr.application.account.GetLoggedAccountUseCase;
import com.centrumhr.application.sync.CreateDataBaseUseCase;
import com.centrumhr.application.sync.IDataBaseService;
import com.centrumhr.application.sync.StartApplicationUseCase;
import com.centrumhr.application.sync.SyncDataBaseUseCase;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class SplashPresenter_Factory implements Factory<SplashPresenter> {
  private final MembersInjector<SplashPresenter> membersInjector;
  private final Provider<IDataBaseService> dataBaseServiceProvider;
  private final Provider<GetLoggedAccountUseCase> getLoggedAccountUseCaseProvider;
  private final Provider<CreateDataBaseUseCase> createDataBaseUseCaseProvider;
  private final Provider<SyncDataBaseUseCase> syncDataBaseUseCaseProvider;
  private final Provider<StartApplicationUseCase> startApplicationUseCaseProvider;

  public SplashPresenter_Factory(MembersInjector<SplashPresenter> membersInjector, Provider<IDataBaseService> dataBaseServiceProvider, Provider<GetLoggedAccountUseCase> getLoggedAccountUseCaseProvider, Provider<CreateDataBaseUseCase> createDataBaseUseCaseProvider, Provider<SyncDataBaseUseCase> syncDataBaseUseCaseProvider, Provider<StartApplicationUseCase> startApplicationUseCaseProvider) {  
    assert membersInjector != null;
    this.membersInjector = membersInjector;
    assert dataBaseServiceProvider != null;
    this.dataBaseServiceProvider = dataBaseServiceProvider;
    assert getLoggedAccountUseCaseProvider != null;
    this.getLoggedAccountUseCaseProvider = getLoggedAccountUseCaseProvider;
    assert createDataBaseUseCaseProvider != null;
    this.createDataBaseUseCaseProvider = createDataBaseUseCaseProvider;
    assert syncDataBaseUseCaseProvider != null;
    this.syncDataBaseUseCaseProvider = syncDataBaseUseCaseProvider;
    assert startApplicationUseCaseProvider != null;
    this.startApplicationUseCaseProvider = startApplicationUseCaseProvider;
  }

  @Override
  public SplashPresenter get() {  
    SplashPresenter instance = new SplashPresenter(dataBaseServiceProvider.get(), getLoggedAccountUseCaseProvider.get(), createDataBaseUseCaseProvider.get(), syncDataBaseUseCaseProvider.get(), startApplicationUseCaseProvider.get());
    membersInjector.injectMembers(instance);
    return instance;
  }

  public static Factory<SplashPresenter> create(MembersInjector<SplashPresenter> membersInjector, Provider<IDataBaseService> dataBaseServiceProvider, Provider<GetLoggedAccountUseCase> getLoggedAccountUseCaseProvider, Provider<CreateDataBaseUseCase> createDataBaseUseCaseProvider, Provider<SyncDataBaseUseCase> syncDataBaseUseCaseProvider, Provider<StartApplicationUseCase> startApplicationUseCaseProvider) {  
    return new SplashPresenter_Factory(membersInjector, dataBaseServiceProvider, getLoggedAccountUseCaseProvider, createDataBaseUseCaseProvider, syncDataBaseUseCaseProvider, startApplicationUseCaseProvider);
  }
}

