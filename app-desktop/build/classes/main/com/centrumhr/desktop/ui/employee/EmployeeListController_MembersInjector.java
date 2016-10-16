package com.centrumhr.desktop.ui.employee;

import com.centrumhr.application.presenter.EmployeeListPresenter;
import com.centrumhr.desktop.core.Controller;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EmployeeListController_MembersInjector implements MembersInjector<EmployeeListController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<EmployeeListPresenter> mPresenterProvider;

  public EmployeeListController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<EmployeeListPresenter> mPresenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(EmployeeListController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<EmployeeListController> create(MembersInjector<Controller> supertypeInjector, Provider<EmployeeListPresenter> mPresenterProvider) {  
      return new EmployeeListController_MembersInjector(supertypeInjector, mPresenterProvider);
  }
}

