package com.centrumhr.desktop.di;

import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.schedule.ScheduleService;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DataBaseModule_ProvidesScheduleServiceFactory implements Factory<ScheduleService> {
  private final DataBaseModule module;
  private final Provider<AttendancePlanFactory> factoryProvider;
  private final Provider<ICalendarService> calendarServiceProvider;

  public DataBaseModule_ProvidesScheduleServiceFactory(DataBaseModule module, Provider<AttendancePlanFactory> factoryProvider, Provider<ICalendarService> calendarServiceProvider) {  
    assert module != null;
    this.module = module;
    assert factoryProvider != null;
    this.factoryProvider = factoryProvider;
    assert calendarServiceProvider != null;
    this.calendarServiceProvider = calendarServiceProvider;
  }

  @Override
  public ScheduleService get() {  
    ScheduleService provided = module.providesScheduleService(factoryProvider.get(), calendarServiceProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ScheduleService> create(DataBaseModule module, Provider<AttendancePlanFactory> factoryProvider, Provider<ICalendarService> calendarServiceProvider) {  
    return new DataBaseModule_ProvidesScheduleServiceFactory(module, factoryProvider, calendarServiceProvider);
  }
}

