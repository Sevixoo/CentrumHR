package com.centrumhr.application.presenter.department;

import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.department.DepartmentUseCaseFactory;
import com.centrumhr.data.model.employment.Department;

import javax.inject.Inject;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class AddDepartmentPresenter {

    public interface View{
        void onAddDepartmentSuccess(Department department);
        void displayError(String message);
    }

    private View mView;

    private DepartmentUseCaseFactory mDepartmentUseCaseFactory;
    private UseCase mPendingUseCase;

    public void setView( View view ){
        this.mView = view;
        if(mView==null&&mPendingUseCase!=null){
            mPendingUseCase.unsubscribe();
        }
    }

    @Inject
    public AddDepartmentPresenter(DepartmentUseCaseFactory mDepartmentUseCaseFactory) {
        this.mDepartmentUseCaseFactory = mDepartmentUseCaseFactory;
    }

    public void addDepartment( String name ){
        mPendingUseCase = mDepartmentUseCaseFactory.createAddDepartmentUseCase( name ).execute(new UseCaseCallback<Department>() {
            @Override
            public void onResult(Department data) {
                mView.onAddDepartmentSuccess(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }

}
