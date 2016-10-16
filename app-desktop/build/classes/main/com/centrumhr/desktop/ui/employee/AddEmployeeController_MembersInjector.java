package com.centrumhr.desktop.ui.employee;

import com.centrumhr.application.presenter.AddEmployeePresenter;
import com.centrumhr.desktop.core.Controller;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AddEmployeeController_MembersInjector implements MembersInjector<AddEmployeeController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<AddEmployeePresenter> mPresenterProvider;

  public AddEmployeeController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<AddEmployeePresenter> mPresenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(AddEmployeeController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<AddEmployeeController> create(MembersInjector<Controller> supertypeInjector, Provider<AddEmployeePresenter> mPresenterProvider) {  
      return new AddEmployeeController_MembersInjector(supertypeInjector, mPresenterProvider);
  }
}

