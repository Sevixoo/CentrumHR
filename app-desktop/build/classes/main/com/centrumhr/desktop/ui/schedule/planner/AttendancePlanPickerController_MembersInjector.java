package com.centrumhr.desktop.ui.schedule.planner;

import com.centrumhr.application.presenter.shedule.AttendancePlanPickerPresenter;
import com.centrumhr.desktop.core.Controller;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AttendancePlanPickerController_MembersInjector implements MembersInjector<AttendancePlanPickerController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<AttendancePlanPickerPresenter> mPresenterProvider;

  public AttendancePlanPickerController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<AttendancePlanPickerPresenter> mPresenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(AttendancePlanPickerController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<AttendancePlanPickerController> create(MembersInjector<Controller> supertypeInjector, Provider<AttendancePlanPickerPresenter> mPresenterProvider) {  
      return new AttendancePlanPickerController_MembersInjector(supertypeInjector, mPresenterProvider);
  }
}

