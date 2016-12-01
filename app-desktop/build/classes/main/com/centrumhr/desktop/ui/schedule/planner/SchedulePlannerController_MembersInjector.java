package com.centrumhr.desktop.ui.schedule.planner;

import com.centrumhr.application.presenter.shedule.AttendancePlanPresenter;
import com.centrumhr.desktop.core.Controller;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class SchedulePlannerController_MembersInjector implements MembersInjector<SchedulePlannerController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<AttendancePlanPresenter> mPresenterProvider;

  public SchedulePlannerController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<AttendancePlanPresenter> mPresenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(SchedulePlannerController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<SchedulePlannerController> create(MembersInjector<Controller> supertypeInjector, Provider<AttendancePlanPresenter> mPresenterProvider) {  
      return new SchedulePlannerController_MembersInjector(supertypeInjector, mPresenterProvider);
  }
}

