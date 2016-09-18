package com.centrumhr.desktop.di;

import com.centrumhr.data.IDataBaseHelper;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_ProvideDataBaseHelperFactory implements Factory<IDataBaseHelper> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideDataBaseHelperFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public IDataBaseHelper get() {  
    IDataBaseHelper provided = module.provideDataBaseHelper();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IDataBaseHelper> create(ApplicationModule module) {  
    return new ApplicationModule_ProvideDataBaseHelperFactory(module);
  }
}

