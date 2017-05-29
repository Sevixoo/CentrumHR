package com.centrumhr.desktop.di;

import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.domain.attendance.IAttendanceHistoryService;
import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.attendance.IProfileService;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_ProvidesAttendancePlanFactoryFactory implements Factory<AttendancePlanFactory> {
  private final ApplicationModule module;
  private final Provider<ICalendarService> calendarServiceProvider;
  private final Provider<IProfileService> profileServiceProvider;
  private final Provider<IAttendanceHistoryService> historyServiceProvider;

  public ApplicationModule_ProvidesAttendancePlanFactoryFactory(ApplicationModule module, Provider<ICalendarService> calendarServiceProvider, Provider<IProfileService> profileServiceProvider, Provider<IAttendanceHistoryService> historyServiceProvider) {  
    assert module != null;
    this.module = module;
    assert calendarServiceProvider != null;
    this.calendarServiceProvider = calendarServiceProvider;
    assert profileServiceProvider != null;
    this.profileServiceProvider = profileServiceProvider;
    assert historyServiceProvider != null;
    this.historyServiceProvider = historyServiceProvider;
  }

  @Override
  public AttendancePlanFactory get() {  
    AttendancePlanFactory provided = module.providesAttendancePlanFactory(calendarServiceProvider.get(), profileServiceProvider.get(), historyServiceProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<AttendancePlanFactory> create(ApplicationModule module, Provider<ICalendarService> calendarServiceProvider, Provider<IProfileService> profileServiceProvider, Provider<IAttendanceHistoryService> historyServiceProvider) {  
    return new ApplicationModule_ProvidesAttendancePlanFactoryFactory(module, calendarServiceProvider, profileServiceProvider, historyServiceProvider);
  }
}

