package com.centrumhr.desktop.di;

import com.centrumhr.application.sync.IDataBaseService;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_ProvideIDataBaseServiceFactory implements Factory<IDataBaseService> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideIDataBaseServiceFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public IDataBaseService get() {  
    IDataBaseService provided = module.provideIDataBaseService();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IDataBaseService> create(ApplicationModule module) {  
    return new ApplicationModule_ProvideIDataBaseServiceFactory(module);
  }
}

