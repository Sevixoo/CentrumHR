package com.centrumhr.desktop.di;
 
import com.centrumhr.application.sync.IORMLiteDataBaseService;
import com.centrumhr.application.sync.IXMLDataBaseService;
import com.centrumhr.desktop.ui.start.SplashPresenter;
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

    IORMLiteDataBaseService getDataBaseService();

    IXMLDataBaseService getXMLDataBaseService();

    LoggedAccountComponent getLoggedAccountComponent( DataBaseModule dataBaseModule , AccountModule accountModule );

}
