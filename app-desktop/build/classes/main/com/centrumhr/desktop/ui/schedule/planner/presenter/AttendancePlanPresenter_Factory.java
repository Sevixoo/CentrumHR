package com.centrumhr.desktop.ui.schedule.planner.presenter;

import com.centrumhr.application.shedule.ChangeAttendanceNameUseCase;
import com.centrumhr.application.shedule.LoadAttendancePlanUseCase;
import com.centrumhr.application.shedule.SaveAttendancePlanUseCase;
import com.centrumhr.application.shedule.SetAttendanceDayTypeUseCase;
import com.centrumhr.domain.attendance.ICalendarService;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AttendancePlanPresenter_Factory implements Factory<AttendancePlanPresenter> {
  private final MembersInjector<AttendancePlanPresenter> membersInjector;
  private final Provider<ICalendarService> calendarServiceProvider;
  private final Provider<LoadAttendancePlanUseCase> loadAttendancePlanUseCaseProvider;
  private final Provider<SaveAttendancePlanUseCase> saveAttendancePlanUseCaseProvider;
  private final Provider<SetAttendanceDayTypeUseCase> setAttendanceDayTypeUseCaseProvider;
  private final Provider<ChangeAttendanceNameUseCase> changeAttendanceNameUseCaseProvider;

  public AttendancePlanPresenter_Factory(MembersInjector<AttendancePlanPresenter> membersInjector, Provider<ICalendarService> calendarServiceProvider, Provider<LoadAttendancePlanUseCase> loadAttendancePlanUseCaseProvider, Provider<SaveAttendancePlanUseCase> saveAttendancePlanUseCaseProvider, Provider<SetAttendanceDayTypeUseCase> setAttendanceDayTypeUseCaseProvider, Provider<ChangeAttendanceNameUseCase> changeAttendanceNameUseCaseProvider) {  
    assert membersInjector != null;
    this.membersInjector = membersInjector;
    assert calendarServiceProvider != null;
    this.calendarServiceProvider = calendarServiceProvider;
    assert loadAttendancePlanUseCaseProvider != null;
    this.loadAttendancePlanUseCaseProvider = loadAttendancePlanUseCaseProvider;
    assert saveAttendancePlanUseCaseProvider != null;
    this.saveAttendancePlanUseCaseProvider = saveAttendancePlanUseCaseProvider;
    assert setAttendanceDayTypeUseCaseProvider != null;
    this.setAttendanceDayTypeUseCaseProvider = setAttendanceDayTypeUseCaseProvider;
    assert changeAttendanceNameUseCaseProvider != null;
    this.changeAttendanceNameUseCaseProvider = changeAttendanceNameUseCaseProvider;
  }

  @Override
  public AttendancePlanPresenter get() {  
    AttendancePlanPresenter instance = new AttendancePlanPresenter(calendarServiceProvider.get(), loadAttendancePlanUseCaseProvider.get(), saveAttendancePlanUseCaseProvider.get(), setAttendanceDayTypeUseCaseProvider.get(), changeAttendanceNameUseCaseProvider.get());
    membersInjector.injectMembers(instance);
    return instance;
  }

  public static Factory<AttendancePlanPresenter> create(MembersInjector<AttendancePlanPresenter> membersInjector, Provider<ICalendarService> calendarServiceProvider, Provider<LoadAttendancePlanUseCase> loadAttendancePlanUseCaseProvider, Provider<SaveAttendancePlanUseCase> saveAttendancePlanUseCaseProvider, Provider<SetAttendanceDayTypeUseCase> setAttendanceDayTypeUseCaseProvider, Provider<ChangeAttendanceNameUseCase> changeAttendanceNameUseCaseProvider) {  
    return new AttendancePlanPresenter_Factory(membersInjector, calendarServiceProvider, loadAttendancePlanUseCaseProvider, saveAttendancePlanUseCaseProvider, setAttendanceDayTypeUseCaseProvider, changeAttendanceNameUseCaseProvider);
  }
}

