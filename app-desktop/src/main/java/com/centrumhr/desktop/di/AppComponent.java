package com.centrumhr.desktop.di;
 
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.application.presenter.SplashPresenter;
import com.centrumhr.desktop.ui.start.SplashController;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by Seweryn on 18.09.2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class })
public interface AppComponent {

    void inject(SplashController controller);

    SplashPresenter getSplashPresenter();
    IDataBaseService getDataBaseService();

    LoggedAccountComponent getLoggedAccountComponent( DataBaseModule dataBaseModule , AccountModule accountModule );

}
