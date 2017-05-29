package com.centrumhr.desktop.di;

import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvidesAttendancePlanRepositoryFactory implements Factory<IAttendancePlanRepository> {
  private final DataBaseModule module;
  private final Provider<AttendancePlanFactory> attendancePlanFactoryProvider;

  public DataBaseModule_ProvidesAttendancePlanRepositoryFactory(DataBaseModule module, Provider<AttendancePlanFactory> attendancePlanFactoryProvider) {  
    assert module != null;
    this.module = module;
    assert attendancePlanFactoryProvider != null;
    this.attendancePlanFactoryProvider = attendancePlanFactoryProvider;
  }

  @Override
  public IAttendancePlanRepository get() {  
    IAttendancePlanRepository provided = module.providesAttendancePlanRepository(attendancePlanFactoryProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IAttendancePlanRepository> create(DataBaseModule module, Provider<AttendancePlanFactory> attendancePlanFactoryProvider) {  
    return new DataBaseModule_ProvidesAttendancePlanRepositoryFactory(module, attendancePlanFactoryProvider);
  }
}

