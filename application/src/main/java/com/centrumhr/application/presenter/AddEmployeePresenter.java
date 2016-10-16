package com.centrumhr.application.presenter;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.employee.EmployeeUseCaseFactory;
import com.centrumhr.data.model.Employee;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
public class AddEmployeePresenter {

    public interface View{
        void showProgress( Message message );
        void hideProgress();
        void displayError( String message );
        void displayAddEmployeeSuccess(Employee employee);
        void dismiss();
        void editEmployee(Employee employee);
    }

    private EmployeeUseCaseFactory  mEmployeeUseCaseFactory;
    private UseCase                 mPendingUseCase;
    private View                    mView;

    @Inject
    public AddEmployeePresenter(EmployeeUseCaseFactory mEmployeeUseCaseFactory) {
        this.mEmployeeUseCaseFactory = mEmployeeUseCaseFactory;
    }

    public void setView( View view ){
        mView = view;
        if(view==null && mPendingUseCase!=null){
            mPendingUseCase.unsubscribe();
        }
    }

    public void addEmployee( Employee employee ){
        mView.showProgress(Message.LOADING);
        mPendingUseCase = mEmployeeUseCaseFactory.createAddEmployeeUseCase(employee).execute(new UseCaseCallback<Employee>() {
            @Override
            public void onResult(Employee data) {
                mView.hideProgress();
                mView.displayAddEmployeeSuccess(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.hideProgress();
                mView.displayError(ex.getMessage());
            }
        });
    }

    public void loadEmployee(UUID uuid){
        mView.showProgress(Message.LOADING);
        mPendingUseCase = mEmployeeUseCaseFactory.createGetEmployeeUseCase(uuid).execute(new UseCaseCallback<Employee>() {
            @Override
            public void onResult(Employee data) {
                mView.hideProgress();
                mView.editEmployee( data );
            }

            @Override
            public void onError(Throwable ex) {
                ex.printStackTrace();
                mView.hideProgress();
                mView.displayError(ex.getMessage());
            }
        });
    }

    public void editEmployee(Employee source,Employee data){
        mView.showProgress(Message.LOADING);
        mPendingUseCase = mEmployeeUseCaseFactory.createEditEmployeeUseCase(source,data).execute(new UseCaseCallback<Employee>() {
            @Override
            public void onResult(Employee data) {
                mView.hideProgress();
                mView.displayAddEmployeeSuccess(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.hideProgress();
                mView.displayError(ex.getMessage());
            }
        });
    }

}
