package com.centrumhr.desktop.di;

import com.centrumhr.data.core.IORMLiteDataBase;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvidesORMLiteDataBaseFactory implements Factory<IORMLiteDataBase> {
  private final DataBaseModule module;

  public DataBaseModule_ProvidesORMLiteDataBaseFactory(DataBaseModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public IORMLiteDataBase get() {  
    IORMLiteDataBase provided = module.providesORMLiteDataBase();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IORMLiteDataBase> create(DataBaseModule module) {  
    return new DataBaseModule_ProvidesORMLiteDataBaseFactory(module);
  }
}

