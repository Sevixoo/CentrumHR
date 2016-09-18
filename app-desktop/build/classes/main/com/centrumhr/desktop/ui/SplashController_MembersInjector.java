package com.centrumhr.desktop.ui;

import com.centrumhr.application.presenter.SplashPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class SplashController_MembersInjector implements MembersInjector<SplashController> {
  private final Provider<SplashPresenter> mPresenterProvider;

  public SplashController_MembersInjector(Provider<SplashPresenter> mPresenterProvider) {  
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  @Override
  public void injectMembers(SplashController instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mPresenter = mPresenterProvider.get();
  }

  public static MembersInjector<SplashController> create(Provider<SplashPresenter> mPresenterProvider) {  
      return new SplashController_MembersInjector(mPresenterProvider);
  }
}

