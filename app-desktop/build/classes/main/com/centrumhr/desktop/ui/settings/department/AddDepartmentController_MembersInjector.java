package com.centrumhr.desktop.ui.settings.department;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.settings.department.presenter.AddDepartmentPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AddDepartmentController_MembersInjector implements MembersInjector<AddDepartmentController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<AddDepartmentPresenter> mPresenterProvider;

  public AddDepartmentController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<AddDepartmentPresenter> mPresenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(AddDepartmentController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<AddDepartmentController> create(MembersInjector<Controller> supertypeInjector, Provider<AddDepartmentPresenter> mPresenterProvider) {  
      return new AddDepartmentController_MembersInjector(supertypeInjector, mPresenterProvider);
  }
}

