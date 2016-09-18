package com.centrumhr.desktop.di;

import com.centrumhr.application.application.account.ILoginService;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_ProvideLoginServiceFactory implements Factory<ILoginService> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideLoginServiceFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public ILoginService get() {  
    ILoginService provided = module.provideLoginService();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ILoginService> create(ApplicationModule module) {  
    return new ApplicationModule_ProvideLoginServiceFactory(module);
  }
}

