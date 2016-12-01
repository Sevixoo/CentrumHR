package com.centrumhr.desktop.ui.schedule.creator;

import com.centrumhr.application.presenter.shedule.AddAttendancePlanPresenter;
import com.centrumhr.desktop.core.Controller;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AddScheduleController_MembersInjector implements MembersInjector<AddScheduleController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<AddAttendancePlanPresenter> mPresenterProvider;

  public AddScheduleController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<AddAttendancePlanPresenter> mPresenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(AddScheduleController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<AddScheduleController> create(MembersInjector<Controller> supertypeInjector, Provider<AddAttendancePlanPresenter> mPresenterProvider) {  
      return new AddScheduleController_MembersInjector(supertypeInjector, mPresenterProvider);
  }
}

