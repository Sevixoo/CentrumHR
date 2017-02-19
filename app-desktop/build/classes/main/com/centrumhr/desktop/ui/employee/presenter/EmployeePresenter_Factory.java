package com.centrumhr.desktop.ui.employee.presenter;

import com.centrumhr.application.employee.RemoveEmployeeUseCase;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EmployeePresenter_Factory implements Factory<EmployeePresenter> {
  private final MembersInjector<EmployeePresenter> membersInjector;
  private final Provider<RemoveEmployeeUseCase> removeEmployeeUseCaseProvider;

  public EmployeePresenter_Factory(MembersInjector<EmployeePresenter> membersInjector, Provider<RemoveEmployeeUseCase> removeEmployeeUseCaseProvider) {  
    assert membersInjector != null;
    this.membersInjector = membersInjector;
    assert removeEmployeeUseCaseProvider != null;
    this.removeEmployeeUseCaseProvider = removeEmployeeUseCaseProvider;
  }

  @Override
  public EmployeePresenter get() {  
    EmployeePresenter instance = new EmployeePresenter(removeEmployeeUseCaseProvider.get());
    membersInjector.injectMembers(instance);
    return instance;
  }

  public static Factory<EmployeePresenter> create(MembersInjector<EmployeePresenter> membersInjector, Provider<RemoveEmployeeUseCase> removeEmployeeUseCaseProvider) {  
    return new EmployeePresenter_Factory(membersInjector, removeEmployeeUseCaseProvider);
  }
}

