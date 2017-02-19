package com.centrumhr.desktop.ui.settings.department.presenter;

import com.centrumhr.application.department.AddDepartmentUseCase;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AddDepartmentPresenter_Factory implements Factory<AddDepartmentPresenter> {
  private final MembersInjector<AddDepartmentPresenter> membersInjector;
  private final Provider<AddDepartmentUseCase> addDepartmentUseCaseProvider;

  public AddDepartmentPresenter_Factory(MembersInjector<AddDepartmentPresenter> membersInjector, Provider<AddDepartmentUseCase> addDepartmentUseCaseProvider) {  
    assert membersInjector != null;
    this.membersInjector = membersInjector;
    assert addDepartmentUseCaseProvider != null;
    this.addDepartmentUseCaseProvider = addDepartmentUseCaseProvider;
  }

  @Override
  public AddDepartmentPresenter get() {  
    AddDepartmentPresenter instance = new AddDepartmentPresenter(addDepartmentUseCaseProvider.get());
    membersInjector.injectMembers(instance);
    return instance;
  }

  public static Factory<AddDepartmentPresenter> create(MembersInjector<AddDepartmentPresenter> membersInjector, Provider<AddDepartmentUseCase> addDepartmentUseCaseProvider) {  
    return new AddDepartmentPresenter_Factory(membersInjector, addDepartmentUseCaseProvider);
  }
}

