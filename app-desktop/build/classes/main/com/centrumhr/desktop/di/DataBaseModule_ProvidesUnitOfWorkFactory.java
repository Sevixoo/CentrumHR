package com.centrumhr.desktop.di;

import com.centrumhr.data.orm.UnitOfWork;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvidesUnitOfWorkFactory implements Factory<UnitOfWork> {
  private final DataBaseModule module;

  public DataBaseModule_ProvidesUnitOfWorkFactory(DataBaseModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public UnitOfWork get() {  
    UnitOfWork provided = module.providesUnitOfWork();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<UnitOfWork> create(DataBaseModule module) {  
    return new DataBaseModule_ProvidesUnitOfWorkFactory(module);
  }
}

