package com.centrumhr.desktop.di;

import com.centrumhr.data.model.employment.EmployeeFactory;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EmploymentModule_ProvideEmployeeFactoryFactory implements Factory<EmployeeFactory> {
  private final EmploymentModule module;

  public EmploymentModule_ProvideEmployeeFactoryFactory(EmploymentModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public EmployeeFactory get() {  
    EmployeeFactory provided = module.provideEmployeeFactory();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<EmployeeFactory> create(EmploymentModule module) {  
    return new EmploymentModule_ProvideEmployeeFactoryFactory(module);
  }
}

