package com.centrumhr.desktop.ui.employee.presenter;

import com.centrumhr.application.employee.LoadEmployeesUseCase;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import rx.Scheduler;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EmployeeListPresenter_Factory implements Factory<EmployeeListPresenter> {
  private final MembersInjector<EmployeeListPresenter> membersInjector;
  private final Provider<Scheduler> schedulerProvider;
  private final Provider<LoadEmployeesUseCase> loadEmployeesUseCaseProvider;

  public EmployeeListPresenter_Factory(MembersInjector<EmployeeListPresenter> membersInjector, Provider<Scheduler> schedulerProvider, Provider<LoadEmployeesUseCase> loadEmployeesUseCaseProvider) {  
    assert membersInjector != null;
    this.membersInjector = membersInjector;
    assert schedulerProvider != null;
    this.schedulerProvider = schedulerProvider;
    assert loadEmployeesUseCaseProvider != null;
    this.loadEmployeesUseCaseProvider = loadEmployeesUseCaseProvider;
  }

  @Override
  public EmployeeListPresenter get() {  
    EmployeeListPresenter instance = new EmployeeListPresenter(schedulerProvider.get(), loadEmployeesUseCaseProvider.get());
    membersInjector.injectMembers(instance);
    return instance;
  }

  public static Factory<EmployeeListPresenter> create(MembersInjector<EmployeeListPresenter> membersInjector, Provider<Scheduler> schedulerProvider, Provider<LoadEmployeesUseCase> loadEmployeesUseCaseProvider) {  
    return new EmployeeListPresenter_Factory(membersInjector, schedulerProvider, loadEmployeesUseCaseProvider);
  }
}

