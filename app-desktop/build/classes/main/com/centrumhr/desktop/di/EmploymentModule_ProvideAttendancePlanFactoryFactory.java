package com.centrumhr.desktop.di;

import com.centrumhr.data.model.attendance.AttendancePlanFactory;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EmploymentModule_ProvideAttendancePlanFactoryFactory implements Factory<AttendancePlanFactory> {
  private final EmploymentModule module;

  public EmploymentModule_ProvideAttendancePlanFactoryFactory(EmploymentModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public AttendancePlanFactory get() {  
    AttendancePlanFactory provided = module.provideAttendancePlanFactory();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<AttendancePlanFactory> create(EmploymentModule module) {  
    return new EmploymentModule_ProvideAttendancePlanFactoryFactory(module);
  }
}

