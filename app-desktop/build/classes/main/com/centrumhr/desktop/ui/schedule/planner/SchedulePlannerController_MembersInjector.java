package com.centrumhr.desktop.ui.schedule.planner;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.planner.presenter.AttendancePlanPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class SchedulePlannerController_MembersInjector implements MembersInjector<SchedulePlannerController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<AttendancePlanPresenter> presenterProvider;

  public SchedulePlannerController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<AttendancePlanPresenter> presenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  @Override
  public void injectMembers(SchedulePlannerController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
  }

  public static MembersInjector<SchedulePlannerController> create(MembersInjector<Controller> supertypeInjector, Provider<AttendancePlanPresenter> presenterProvider) {  
      return new SchedulePlannerController_MembersInjector(supertypeInjector, presenterProvider);
  }
}

