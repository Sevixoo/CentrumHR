package com.centrumhr.application.presenter;

import com.centrumhr.application.application.account.exception.AccountNotExists;
import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.application.account.AccountUseCaseFactory;
import com.centrumhr.application.application.account.data.AccountData;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.sync.IDataBaseService;
import com.centrumhr.application.application.sync.SyncUseCaseFactory;
import com.centrumhr.data.orm.IORMLiteDataBase;


import javax.inject.Inject;

public class SplashPresenter {

    public interface View{
        void showProgress( Message message );
        void hideProgress();
        void displayError( String message );
        void displayLoginScreen();
        void displayMainScreen();
    }

    private AccountUseCaseFactory   mAccountUseCaseFactory;
    private SyncUseCaseFactory      mSyncUseCaseFactory;
    private UseCase                 mPendingUseCase;
    private IDataBaseService        mDataBaseService;
    private View                    mView;

    @Inject
    public SplashPresenter(AccountUseCaseFactory accountUseCaseFactory , SyncUseCaseFactory syncUseCaseFactory , IDataBaseService dataBaseService) {
        this.mAccountUseCaseFactory = accountUseCaseFactory;
        this.mSyncUseCaseFactory = syncUseCaseFactory;
        this.mDataBaseService = dataBaseService;
    }

    public void setView(View view ){
        mView = view;
        if(view==null && mPendingUseCase!=null){
            mPendingUseCase.unsubscribe();
        }
    }

    public void checkIfAccountIsLogged(){
        mView.showProgress(Message.LOADING);
        mPendingUseCase = mAccountUseCaseFactory.createGetLoggedAccountUseCase().execute(new UseCaseCallback<AccountData>() {
            @Override
            public void onResult(AccountData accountData) {
                if(mDataBaseService.dataBaseExists(accountData)){
                    startApplication();
                }else{
                    createDataBase( accountData );
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                if(e instanceof AccountNotExists){
                    mView.displayLoginScreen();
                }else {
                    mView.displayError(e.getMessage());
                }
            }
        });
    }

    public void onAccountCreated( AccountData accountData ){
        if(mDataBaseService.dataBaseExists(accountData)){
            syncDataBase();
        }else{
            createDataBase( accountData );
        }
    }

    private void createDataBase( AccountData accountData ){
        mView.showProgress(Message.CREATING_DATABASE);
        mPendingUseCase = mSyncUseCaseFactory.createCreateDataBaseUseCase( accountData ).execute(new UseCaseCallback<IORMLiteDataBase>() {
            @Override
            public void onResult(IORMLiteDataBase data) {
                syncDataBase();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                mView.displayError(e.getMessage());
            }
        });
    }

    private void syncDataBase(){
        mView.showProgress(Message.SYNC_DATABASE);
        mPendingUseCase = mSyncUseCaseFactory.createSyncDataBaseUseCase().execute(new UseCaseCallback<Boolean>() {
            @Override
            public void onResult(Boolean data) {
                startApplication();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                mView.displayError(e.getMessage());
            }
        });
    }

    private void startApplication(){
        mView.showProgress(Message.APPLICATION_START);
        mPendingUseCase = mSyncUseCaseFactory.createStartApplicationUseCase().execute(new UseCaseCallback<Boolean>() {
            @Override
            public void onResult(Boolean data) {
                try {
                    mView.displayMainScreen();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                mView.displayError(e.getMessage());
            }
        });
    }

}
