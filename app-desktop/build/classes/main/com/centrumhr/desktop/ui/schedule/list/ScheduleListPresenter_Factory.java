package com.centrumhr.desktop.ui.schedule.list;

import com.centrumhr.application.shedule.LoadScheduleListUseCase;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ScheduleListPresenter_Factory implements Factory<ScheduleListPresenter> {
  private final MembersInjector<ScheduleListPresenter> membersInjector;
  private final Provider<LoadScheduleListUseCase> loadScheduleListUseCaseProvider;

  public ScheduleListPresenter_Factory(MembersInjector<ScheduleListPresenter> membersInjector, Provider<LoadScheduleListUseCase> loadScheduleListUseCaseProvider) {  
    assert membersInjector != null;
    this.membersInjector = membersInjector;
    assert loadScheduleListUseCaseProvider != null;
    this.loadScheduleListUseCaseProvider = loadScheduleListUseCaseProvider;
  }

  @Override
  public ScheduleListPresenter get() {  
    ScheduleListPresenter instance = new ScheduleListPresenter(loadScheduleListUseCaseProvider.get());
    membersInjector.injectMembers(instance);
    return instance;
  }

  public static Factory<ScheduleListPresenter> create(MembersInjector<ScheduleListPresenter> membersInjector, Provider<LoadScheduleListUseCase> loadScheduleListUseCaseProvider) {  
    return new ScheduleListPresenter_Factory(membersInjector, loadScheduleListUseCaseProvider);
  }
}

