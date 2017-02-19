package com.centrumhr.desktop.ui.settings.department.presenter;

import com.centrumhr.application.department.DeleteDepartmentUseCase;
import com.centrumhr.application.department.LoadDepartmentsUseCase;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DepartmentPresenter_Factory implements Factory<DepartmentPresenter> {
  private final MembersInjector<DepartmentPresenter> membersInjector;
  private final Provider<LoadDepartmentsUseCase> loadDepartmentsUseCaseProvider;
  private final Provider<DeleteDepartmentUseCase> deleteDepartmentUseCaseProvider;

  public DepartmentPresenter_Factory(MembersInjector<DepartmentPresenter> membersInjector, Provider<LoadDepartmentsUseCase> loadDepartmentsUseCaseProvider, Provider<DeleteDepartmentUseCase> deleteDepartmentUseCaseProvider) {  
    assert membersInjector != null;
    this.membersInjector = membersInjector;
    assert loadDepartmentsUseCaseProvider != null;
    this.loadDepartmentsUseCaseProvider = loadDepartmentsUseCaseProvider;
    assert deleteDepartmentUseCaseProvider != null;
    this.deleteDepartmentUseCaseProvider = deleteDepartmentUseCaseProvider;
  }

  @Override
  public DepartmentPresenter get() {  
    DepartmentPresenter instance = new DepartmentPresenter(loadDepartmentsUseCaseProvider.get(), deleteDepartmentUseCaseProvider.get());
    membersInjector.injectMembers(instance);
    return instance;
  }

  public static Factory<DepartmentPresenter> create(MembersInjector<DepartmentPresenter> membersInjector, Provider<LoadDepartmentsUseCase> loadDepartmentsUseCaseProvider, Provider<DeleteDepartmentUseCase> deleteDepartmentUseCaseProvider) {  
    return new DepartmentPresenter_Factory(membersInjector, loadDepartmentsUseCaseProvider, deleteDepartmentUseCaseProvider);
  }
}

