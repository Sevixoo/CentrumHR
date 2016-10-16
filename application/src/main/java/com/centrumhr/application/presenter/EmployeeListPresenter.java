package com.centrumhr.application.presenter;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.employee.EmployeeUseCaseFactory;
import com.centrumhr.data.model.Employee;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class EmployeeListPresenter {

    public interface View{
        void showProgress( Message message );
        void hideProgress();
        void displayError( String message );
        void displayAddedEmployee(Employee employee);
        void displayEmployeeList( List<Employee> employees );
    }

    private View mView;
    private UseCase mPendingUseCase;
    private EmployeeUseCaseFactory mEmployeeUseCaseFactory;

    @Inject
    public EmployeeListPresenter(EmployeeUseCaseFactory mEmployeeUseCaseFactory) {
        this.mEmployeeUseCaseFactory = mEmployeeUseCaseFactory;
    }

    public void setView(View mView) {
        this.mView = mView;
    }

    public void loadEmployees(){
        mView.showProgress(Message.LOADING);
        mPendingUseCase = mEmployeeUseCaseFactory.createLoadEmployeesUseCase().execute(new UseCaseCallback<List<Employee>>() {
            @Override
            public void onResult(List<Employee> data) {
                mView.displayEmployeeList(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.hideProgress();
                mView.displayError(ex.getMessage());
            }
        });
    }

}
