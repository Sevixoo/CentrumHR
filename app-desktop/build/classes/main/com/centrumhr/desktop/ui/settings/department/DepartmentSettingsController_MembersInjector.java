package com.centrumhr.desktop.ui.settings.department;

import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.settings.department.presenter.DepartmentPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DepartmentSettingsController_MembersInjector implements MembersInjector<DepartmentSettingsController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<DepartmentPresenter> mPresenterProvider;

  public DepartmentSettingsController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<DepartmentPresenter> mPresenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(DepartmentSettingsController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<DepartmentSettingsController> create(MembersInjector<Controller> supertypeInjector, Provider<DepartmentPresenter> mPresenterProvider) {  
      return new DepartmentSettingsController_MembersInjector(supertypeInjector, mPresenterProvider);
  }
}

