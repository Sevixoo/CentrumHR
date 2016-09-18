package com.centrumhr.desktop.di;

import com.centrumhr.application.application.common.IPostExecutionThread;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_ProvidePostExecutionThreadFactory implements Factory<IPostExecutionThread> {
  private final ApplicationModule module;

  public ApplicationModule_ProvidePostExecutionThreadFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public IPostExecutionThread get() {  
    IPostExecutionThread provided = module.providePostExecutionThread();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IPostExecutionThread> create(ApplicationModule module) {  
    return new ApplicationModule_ProvidePostExecutionThreadFactory(module);
  }
}

