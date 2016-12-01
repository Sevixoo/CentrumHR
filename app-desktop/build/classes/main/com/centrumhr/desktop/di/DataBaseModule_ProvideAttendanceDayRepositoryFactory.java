package com.centrumhr.desktop.di;

import com.centrumhr.data.domain.IAttendanceDayRepository;
import com.centrumhr.desktop.data.RepositoryFactory;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvideAttendanceDayRepositoryFactory implements Factory<IAttendanceDayRepository> {
  private final DataBaseModule module;
  private final Provider<RepositoryFactory> repositoryFactoryProvider;

  public DataBaseModule_ProvideAttendanceDayRepositoryFactory(DataBaseModule module, Provider<RepositoryFactory> repositoryFactoryProvider) {  
    assert module != null;
    this.module = module;
    assert repositoryFactoryProvider != null;
    this.repositoryFactoryProvider = repositoryFactoryProvider;
  }

  @Override
  public IAttendanceDayRepository get() {  
    IAttendanceDayRepository provided = module.provideAttendanceDayRepository(repositoryFactoryProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IAttendanceDayRepository> create(DataBaseModule module, Provider<RepositoryFactory> repositoryFactoryProvider) {  
    return new DataBaseModule_ProvideAttendanceDayRepositoryFactory(module, repositoryFactoryProvider);
  }
}

