package com.centrumhr.desktop.ui.schedule.creator;

import com.centrumhr.application.employee.LoadEmployeesUseCase;
import com.centrumhr.application.shedule.CreateAttendancePlanUseCase;
import com.centrumhr.domain.attendance.ICalendarService;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AddSchedulePresenter_Factory implements Factory<AddSchedulePresenter> {
  private final MembersInjector<AddSchedulePresenter> membersInjector;
  private final Provider<ICalendarService> calendarServiceProvider;
  private final Provider<LoadEmployeesUseCase> loadEmployeesUseCaseProvider;
  private final Provider<CreateAttendancePlanUseCase> createAttendancePlanUseCaseProvider;

  public AddSchedulePresenter_Factory(MembersInjector<AddSchedulePresenter> membersInjector, Provider<ICalendarService> calendarServiceProvider, Provider<LoadEmployeesUseCase> loadEmployeesUseCaseProvider, Provider<CreateAttendancePlanUseCase> createAttendancePlanUseCaseProvider) {  
    assert membersInjector != null;
    this.membersInjector = membersInjector;
    assert calendarServiceProvider != null;
    this.calendarServiceProvider = calendarServiceProvider;
    assert loadEmployeesUseCaseProvider != null;
    this.loadEmployeesUseCaseProvider = loadEmployeesUseCaseProvider;
    assert createAttendancePlanUseCaseProvider != null;
    this.createAttendancePlanUseCaseProvider = createAttendancePlanUseCaseProvider;
  }

  @Override
  public AddSchedulePresenter get() {  
    AddSchedulePresenter instance = new AddSchedulePresenter(calendarServiceProvider.get(), loadEmployeesUseCaseProvider.get(), createAttendancePlanUseCaseProvider.get());
    membersInjector.injectMembers(instance);
    return instance;
  }

  public static Factory<AddSchedulePresenter> create(MembersInjector<AddSchedulePresenter> membersInjector, Provider<ICalendarService> calendarServiceProvider, Provider<LoadEmployeesUseCase> loadEmployeesUseCaseProvider, Provider<CreateAttendancePlanUseCase> createAttendancePlanUseCaseProvider) {  
    return new AddSchedulePresenter_Factory(membersInjector, calendarServiceProvider, loadEmployeesUseCaseProvider, createAttendancePlanUseCaseProvider);
  }
}

