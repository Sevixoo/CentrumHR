package com.centrumhr.desktop.di;

import com.centrumhr.application.application.account.IAccountService;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_ProvideAccountServiceFactory implements Factory<IAccountService> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideAccountServiceFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public IAccountService get() {  
    IAccountService provided = module.provideAccountService();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IAccountService> create(ApplicationModule module) {  
    return new ApplicationModule_ProvideAccountServiceFactory(module);
  }
}

