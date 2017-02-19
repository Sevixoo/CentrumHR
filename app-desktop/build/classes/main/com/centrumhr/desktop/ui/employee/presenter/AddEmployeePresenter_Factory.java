package com.centrumhr.desktop.ui.employee.presenter;

import com.centrumhr.application.department.LoadDepartmentsUseCase;
import com.centrumhr.application.employee.AddEmployeeUseCase;
import com.centrumhr.application.employee.EditEmployeeUseCase;
import com.centrumhr.application.employee.GetEmployeeUseCase;
import com.centrumhr.application.workFunction.LoadWorkFunctionsUseCase;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AddEmployeePresenter_Factory implements Factory<AddEmployeePresenter> {
  private final MembersInjector<AddEmployeePresenter> membersInjector;
  private final Provider<LoadWorkFunctionsUseCase> loadWorkFunctionsUseCaseProvider;
  private final Provider<LoadDepartmentsUseCase> loadDepartmentsUseCaseProvider;
  private final Provider<AddEmployeeUseCase> addEmployeeUseCaseProvider;
  private final Provider<GetEmployeeUseCase> getEmployeeUseCaseProvider;
  private final Provider<EditEmployeeUseCase> editEmployeeUseCaseProvider;

  public AddEmployeePresenter_Factory(MembersInjector<AddEmployeePresenter> membersInjector, Provider<LoadWorkFunctionsUseCase> loadWorkFunctionsUseCaseProvider, Provider<LoadDepartmentsUseCase> loadDepartmentsUseCaseProvider, Provider<AddEmployeeUseCase> addEmployeeUseCaseProvider, Provider<GetEmployeeUseCase> getEmployeeUseCaseProvider, Provider<EditEmployeeUseCase> editEmployeeUseCaseProvider) {  
    assert membersInjector != null;
    this.membersInjector = membersInjector;
    assert loadWorkFunctionsUseCaseProvider != null;
    this.loadWorkFunctionsUseCaseProvider = loadWorkFunctionsUseCaseProvider;
    assert loadDepartmentsUseCaseProvider != null;
    this.loadDepartmentsUseCaseProvider = loadDepartmentsUseCaseProvider;
    assert addEmployeeUseCaseProvider != null;
    this.addEmployeeUseCaseProvider = addEmployeeUseCaseProvider;
    assert getEmployeeUseCaseProvider != null;
    this.getEmployeeUseCaseProvider = getEmployeeUseCaseProvider;
    assert editEmployeeUseCaseProvider != null;
    this.editEmployeeUseCaseProvider = editEmployeeUseCaseProvider;
  }

  @Override
  public AddEmployeePresenter get() {  
    AddEmployeePresenter instance = new AddEmployeePresenter(loadWorkFunctionsUseCaseProvider.get(), loadDepartmentsUseCaseProvider.get(), addEmployeeUseCaseProvider.get(), getEmployeeUseCaseProvider.get(), editEmployeeUseCaseProvider.get());
    membersInjector.injectMembers(instance);
    return instance;
  }

  public static Factory<AddEmployeePresenter> create(MembersInjector<AddEmployeePresenter> membersInjector, Provider<LoadWorkFunctionsUseCase> loadWorkFunctionsUseCaseProvider, Provider<LoadDepartmentsUseCase> loadDepartmentsUseCaseProvider, Provider<AddEmployeeUseCase> addEmployeeUseCaseProvider, Provider<GetEmployeeUseCase> getEmployeeUseCaseProvider, Provider<EditEmployeeUseCase> editEmployeeUseCaseProvider) {  
    return new AddEmployeePresenter_Factory(membersInjector, loadWorkFunctionsUseCaseProvider, loadDepartmentsUseCaseProvider, addEmployeeUseCaseProvider, getEmployeeUseCaseProvider, editEmployeeUseCaseProvider);
  }
}

