package com.centrumhr.desktop.ui.schedule.list;

import com.centrumhr.desktop.core.Controller;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ScheduleListController_MembersInjector implements MembersInjector<ScheduleListController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<ScheduleListPresenter> presenterProvider;

  public ScheduleListController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<ScheduleListPresenter> presenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  @Override
  public void injectMembers(ScheduleListController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
  }

  public static MembersInjector<ScheduleListController> create(MembersInjector<Controller> supertypeInjector, Provider<ScheduleListPresenter> presenterProvider) {  
      return new ScheduleListController_MembersInjector(supertypeInjector, presenterProvider);
  }
}

