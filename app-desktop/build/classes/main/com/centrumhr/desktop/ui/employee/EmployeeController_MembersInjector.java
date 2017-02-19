package com.centrumhr.desktop.ui.employee;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.employee.presenter.EmployeePresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EmployeeController_MembersInjector implements MembersInjector<EmployeeController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<EmployeePresenter> mPresenterProvider;

  public EmployeeController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<EmployeePresenter> mPresenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(EmployeeController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<EmployeeController> create(MembersInjector<Controller> supertypeInjector, Provider<EmployeePresenter> mPresenterProvider) {  
      return new EmployeeController_MembersInjector(supertypeInjector, mPresenterProvider);
  }
}

