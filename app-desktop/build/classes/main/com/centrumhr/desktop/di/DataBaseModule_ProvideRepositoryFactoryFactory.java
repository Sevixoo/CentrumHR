package com.centrumhr.desktop.di;

import com.centrumhr.data.orm.UnitOfWork;
import com.centrumhr.desktop.data.RepositoryFactory;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvideRepositoryFactoryFactory implements Factory<RepositoryFactory> {
  private final DataBaseModule module;
  private final Provider<UnitOfWork> unitOfWorkProvider;

  public DataBaseModule_ProvideRepositoryFactoryFactory(DataBaseModule module, Provider<UnitOfWork> unitOfWorkProvider) {  
    assert module != null;
    this.module = module;
    assert unitOfWorkProvider != null;
    this.unitOfWorkProvider = unitOfWorkProvider;
  }

  @Override
  public RepositoryFactory get() {  
    RepositoryFactory provided = module.provideRepositoryFactory(unitOfWorkProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<RepositoryFactory> create(DataBaseModule module, Provider<UnitOfWork> unitOfWorkProvider) {  
    return new DataBaseModule_ProvideRepositoryFactoryFactory(module, unitOfWorkProvider);
  }
}

