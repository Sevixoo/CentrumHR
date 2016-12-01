package com.centrumhr.application.presenter;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.employee.EmployeeUseCaseFactory;
import com.centrumhr.data.model.employment.Employee;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class EmployeePresenter {

    public interface View{
        void showProgress(Message message);
        void hideProgress();
        void displayError(String message);
        void onUserDeleted();
    }

    private View mView;
    private UseCase mPendingUseCase;
    private EmployeeUseCaseFactory mEmployeeUseCaseFactory;

    @Inject
    public EmployeePresenter(EmployeeUseCaseFactory mEmployeeUseCaseFactory) {
        this.mEmployeeUseCaseFactory = mEmployeeUseCaseFactory;
    }

    public void setView(View mView) {
        this.mView = mView;
    }

    public void deleteEmployee(UUID uniqueId){
        mView.showProgress(Message.LOADING);
        mPendingUseCase = mEmployeeUseCaseFactory.createRemoveEmployeeUseCase(uniqueId).execute(new UseCaseCallback<Boolean>() {
            @Override
            public void onResult(Boolean data) {
                mView.onUserDeleted();
            }
            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }

}
