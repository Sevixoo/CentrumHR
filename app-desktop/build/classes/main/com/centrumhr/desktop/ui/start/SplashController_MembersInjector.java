package com.centrumhr.desktop.ui.start;

import com.centrumhr.application.presenter.SplashPresenter;
import com.centrumhr.desktop.core.Controller;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class SplashController_MembersInjector implements MembersInjector<SplashController> {
  private final MembersInjector<Controller> supertypeInjector;
  private final Provider<SplashPresenter> mPresenterProvider;

  public SplashController_MembersInjector(MembersInjector<Controller> supertypeInjector, Provider<SplashPresenter> mPresenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(SplashController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<SplashController> create(MembersInjector<Controller> supertypeInjector, Provider<SplashPresenter> mPresenterProvider) {  
      return new SplashController_MembersInjector(supertypeInjector, mPresenterProvider);
  }
}

