package com.centrumhr.desktop.ui.schedule.creator;

import com.centrumhr.desktop.core.Controller;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AddScheduleController_MembersInjector implements MembersInjector<AddScheduleController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<AddSchedulePresenter> presenterProvider;

  public AddScheduleController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<AddSchedulePresenter> presenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  @Override
  public void injectMembers(AddScheduleController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
  }

  public static MembersInjector<AddScheduleController> create(MembersInjector<Controller> supertypeInjector, Provider<AddSchedulePresenter> presenterProvider) {  
      return new AddScheduleController_MembersInjector(supertypeInjector, presenterProvider);
  }
}

