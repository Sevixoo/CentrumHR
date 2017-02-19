package com.centrumhr.desktop.di;

import com.centrumhr.domain.attendance.ICalendarService;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_ProvidesCalendarServiceFactory implements Factory<ICalendarService> {
  private final ApplicationModule module;

  public ApplicationModule_ProvidesCalendarServiceFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public ICalendarService get() {  
    ICalendarService provided = module.providesCalendarService();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ICalendarService> create(ApplicationModule module) {  
    return new ApplicationModule_ProvidesCalendarServiceFactory(module);
  }
}

