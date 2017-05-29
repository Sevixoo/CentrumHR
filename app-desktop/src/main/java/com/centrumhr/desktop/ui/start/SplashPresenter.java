package com.centrumhr.desktop.ui.start;

import com.centrumhr.application.account.exception.AccountNotExists;
import com.centrumhr.application.account.GetLoggedAccountUseCase;
import com.centrumhr.application.core.Presenter;
import com.centrumhr.application.core.UI;
import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.Message;
import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.application.sync.*;
import rx.schedulers.JavaFxScheduler;


import javax.inject.Inject;

public class SplashPresenter extends Presenter<SplashPresenter.View>{

    public interface View extends UI{
        void showProgress( Message message );
        void displayLoginScreen();
        void displayMainScreen();
    }

    private IORMLiteDataBaseService dataBaseService;
    private IXMLDataBaseService xmlDataBaseService;
    private GetLoggedAccountUseCase getLoggedAccountUseCase;
    private CreateDataBaseUseCase   createDataBaseUseCase;
    private SyncDataBaseUseCase     syncDataBaseUseCase;
    private StartApplicationUseCase startApplicationUseCase;

    @Inject
    public SplashPresenter(IXMLDataBaseService xmlDataBaseService, IORMLiteDataBaseService dataBaseService, GetLoggedAccountUseCase getLoggedAccountUseCase, CreateDataBaseUseCase createDataBaseUseCase, SyncDataBaseUseCase syncDataBaseUseCase, StartApplicationUseCase startApplicationUseCase) {
        super(JavaFxScheduler.getInstance(),UseCaseThreadExecutor.INSTANCE);
        this.dataBaseService = dataBaseService;
        this.getLoggedAccountUseCase = getLoggedAccountUseCase;
        this.createDataBaseUseCase = createDataBaseUseCase;
        this.syncDataBaseUseCase = syncDataBaseUseCase;
        this.startApplicationUseCase = startApplicationUseCase;
        this.xmlDataBaseService = xmlDataBaseService;
    }

    public void checkIfAccountIsLogged(){
        view.showProgress(Message.LOADING);
        executeUseCase(getLoggedAccountUseCase, false, accountData -> {
            if(dataBaseService.dataBaseExists(accountData)&&xmlDataBaseService.dataBaseExists(accountData)){
                startApplication();
            }else{
                createDataBase( accountData );
            }
        },throwable -> {
            if(throwable instanceof AccountNotExists){
                view.displayLoginScreen();
            }else {
                view.displayError(throwable);
            }
        });
    }

    public void onAccountCreated( AccountData accountData ){
        if(dataBaseService.dataBaseExists(accountData)){
            syncDataBase();
        }else{
            createDataBase( accountData );
        }
    }

    private void createDataBase( AccountData accountData ){
        view.showProgress(Message.CREATING_DATABASE);
        executeUseCase(createDataBaseUseCase,accountData,dataBase -> {
            syncDataBase();
        },throwable -> {
            view.hideProgress();
            view.displayError(throwable);
        });
    }

    private void syncDataBase(){
        view.showProgress(Message.SYNC_DATABASE);
        executeUseCase(syncDataBaseUseCase,false,aBoolean -> {
            startApplication();
        },throwable -> {
            view.hideProgress();
            view.displayError(throwable);
        });
    }

    private void startApplication(){
        view.showProgress(Message.APPLICATION_START);
        executeUseCase(startApplicationUseCase,false,aBoolean -> {
            try {
                view.displayMainScreen();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        },throwable -> {
            view.hideProgress();
            view.displayError(throwable);
        });
    }


}
