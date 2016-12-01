package com.centrumhr.desktop.di;

import com.centrumhr.data.domain.IAttendanceDayRepository;
import com.centrumhr.data.domain.IAttendanceEmployeeRepository;
import com.centrumhr.data.domain.IAttendancePlanRepository;
import com.centrumhr.data.importer.AttendancePlanImporter;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvideAttendancePlanImporterFactory implements Factory<AttendancePlanImporter> {
  private final DataBaseModule module;
  private final Provider<IAttendanceEmployeeRepository> mAttendanceEmployeeRepositoryProvider;
  private final Provider<IAttendancePlanRepository> mAttendancePlanRepositoryProvider;
  private final Provider<IAttendanceDayRepository> mAttendanceDayRepositoryProvider;

  public DataBaseModule_ProvideAttendancePlanImporterFactory(DataBaseModule module, Provider<IAttendanceEmployeeRepository> mAttendanceEmployeeRepositoryProvider, Provider<IAttendancePlanRepository> mAttendancePlanRepositoryProvider, Provider<IAttendanceDayRepository> mAttendanceDayRepositoryProvider) {  
    assert module != null;
    this.module = module;
    assert mAttendanceEmployeeRepositoryProvider != null;
    this.mAttendanceEmployeeRepositoryProvider = mAttendanceEmployeeRepositoryProvider;
    assert mAttendancePlanRepositoryProvider != null;
    this.mAttendancePlanRepositoryProvider = mAttendancePlanRepositoryProvider;
    assert mAttendanceDayRepositoryProvider != null;
    this.mAttendanceDayRepositoryProvider = mAttendanceDayRepositoryProvider;
  }

  @Override
  public AttendancePlanImporter get() {  
    AttendancePlanImporter provided = module.provideAttendancePlanImporter(mAttendanceEmployeeRepositoryProvider.get(), mAttendancePlanRepositoryProvider.get(), mAttendanceDayRepositoryProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<AttendancePlanImporter> create(DataBaseModule module, Provider<IAttendanceEmployeeRepository> mAttendanceEmployeeRepositoryProvider, Provider<IAttendancePlanRepository> mAttendancePlanRepositoryProvider, Provider<IAttendanceDayRepository> mAttendanceDayRepositoryProvider) {  
    return new DataBaseModule_ProvideAttendancePlanImporterFactory(module, mAttendanceEmployeeRepositoryProvider, mAttendancePlanRepositoryProvider, mAttendanceDayRepositoryProvider);
  }
}

