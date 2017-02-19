package com.centrumhr.desktop.di;

import com.centrumhr.domain.attendance.AttendancePlanFactory;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EmploymentModule_ProvidesAttendancePlanFactoryFactory implements Factory<AttendancePlanFactory> {
  private final EmploymentModule module;

  public EmploymentModule_ProvidesAttendancePlanFactoryFactory(EmploymentModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public AttendancePlanFactory get() {  
    AttendancePlanFactory provided = module.providesAttendancePlanFactory();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<AttendancePlanFactory> create(EmploymentModule module) {  
    return new EmploymentModule_ProvidesAttendancePlanFactoryFactory(module);
  }
}

