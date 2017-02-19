package com.centrumhr.desktop.ui.employee;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.employee.presenter.AddEmployeePresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AddEmployeeController_MembersInjector implements MembersInjector<AddEmployeeController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<AddEmployeePresenter> presenterProvider;

  public AddEmployeeController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<AddEmployeePresenter> presenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  @Override
  public void injectMembers(AddEmployeeController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
  }

  public static MembersInjector<AddEmployeeController> create(MembersInjector<Controller> supertypeInjector, Provider<AddEmployeePresenter> presenterProvider) {  
      return new AddEmployeeController_MembersInjector(supertypeInjector, presenterProvider);
  }
}

