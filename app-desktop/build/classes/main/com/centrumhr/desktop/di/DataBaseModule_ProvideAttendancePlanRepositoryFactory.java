package com.centrumhr.desktop.di;

import com.centrumhr.data.domain.IAttendancePlanRepository;
import com.centrumhr.desktop.data.RepositoryFactory;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvideAttendancePlanRepositoryFactory implements Factory<IAttendancePlanRepository> {
  private final DataBaseModule module;
  private final Provider<RepositoryFactory> repositoryFactoryProvider;

  public DataBaseModule_ProvideAttendancePlanRepositoryFactory(DataBaseModule module, Provider<RepositoryFactory> repositoryFactoryProvider) {  
    assert module != null;
    this.module = module;
    assert repositoryFactoryProvider != null;
    this.repositoryFactoryProvider = repositoryFactoryProvider;
  }

  @Override
  public IAttendancePlanRepository get() {  
    IAttendancePlanRepository provided = module.provideAttendancePlanRepository(repositoryFactoryProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IAttendancePlanRepository> create(DataBaseModule module, Provider<RepositoryFactory> repositoryFactoryProvider) {  
    return new DataBaseModule_ProvideAttendancePlanRepositoryFactory(module, repositoryFactoryProvider);
  }
}

