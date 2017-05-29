package com.centrumhr.desktop.di;

import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.model.employment.IEmployeeRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvidesEmployeeRepositoryFactory implements Factory<IEmployeeRepository> {
  private final DataBaseModule module;
  private final Provider<IORMLiteDataBase> dataBaseProvider;

  public DataBaseModule_ProvidesEmployeeRepositoryFactory(DataBaseModule module, Provider<IORMLiteDataBase> dataBaseProvider) {  
    assert module != null;
    this.module = module;
    assert dataBaseProvider != null;
    this.dataBaseProvider = dataBaseProvider;
  }

  @Override
  public IEmployeeRepository get() {  
    IEmployeeRepository provided = module.providesEmployeeRepository(dataBaseProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IEmployeeRepository> create(DataBaseModule module, Provider<IORMLiteDataBase> dataBaseProvider) {  
    return new DataBaseModule_ProvidesEmployeeRepositoryFactory(module, dataBaseProvider);
  }
}

