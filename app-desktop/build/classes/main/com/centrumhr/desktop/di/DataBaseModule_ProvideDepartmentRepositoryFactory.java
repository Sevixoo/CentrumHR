package com.centrumhr.desktop.di;

import com.centrumhr.data.domain.IDepartmentRepository;
import com.centrumhr.desktop.data.RepositoryFactory;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvideDepartmentRepositoryFactory implements Factory<IDepartmentRepository> {
  private final DataBaseModule module;
  private final Provider<RepositoryFactory> repositoryFactoryProvider;

  public DataBaseModule_ProvideDepartmentRepositoryFactory(DataBaseModule module, Provider<RepositoryFactory> repositoryFactoryProvider) {  
    assert module != null;
    this.module = module;
    assert repositoryFactoryProvider != null;
    this.repositoryFactoryProvider = repositoryFactoryProvider;
  }

  @Override
  public IDepartmentRepository get() {  
    IDepartmentRepository provided = module.provideDepartmentRepository(repositoryFactoryProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IDepartmentRepository> create(DataBaseModule module, Provider<RepositoryFactory> repositoryFactoryProvider) {  
    return new DataBaseModule_ProvideDepartmentRepositoryFactory(module, repositoryFactoryProvider);
  }
}

