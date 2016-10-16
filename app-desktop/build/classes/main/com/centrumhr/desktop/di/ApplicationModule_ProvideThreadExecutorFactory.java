package com.centrumhr.desktop.di;

import com.centrumhr.application.application.common.IExecutor;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_ProvideThreadExecutorFactory implements Factory<IExecutor> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideThreadExecutorFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public IExecutor get() {  
    IExecutor provided = module.provideThreadExecutor();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IExecutor> create(ApplicationModule module) {  
    return new ApplicationModule_ProvideThreadExecutorFactory(module);
  }
}

