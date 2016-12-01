package com.centrumhr.application.presenter.department;

import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.department.DepartmentUseCaseFactory;
import com.centrumhr.application.application.shedule.ScheduleUseCaseFactory;
import com.centrumhr.application.presenter.shedule.AttendancePlanPickerPresenter;
import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.employment.Department;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class DepartmentPresenter {

    public interface View{
        void displayDepartmentList(List<Department> data);
        void displayError( String message );
        void onDepartmentDeleted(UUID uuid);
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
    public DepartmentPresenter(DepartmentUseCaseFactory mDepartmentUseCaseFactory) {
        this.mDepartmentUseCaseFactory = mDepartmentUseCaseFactory;
    }

    public void loadDepartments(){
        mPendingUseCase = mDepartmentUseCaseFactory.createLoadDepartmentsUseCase().execute(new UseCaseCallback<List<Department>>() {
            @Override
            public void onResult(List<Department> data) {
                mView.displayDepartmentList(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }

    public void deleteDepartment( UUID uuid ){
        mPendingUseCase = mDepartmentUseCaseFactory.createDeleteDepartmentUseCase(uuid).execute(new UseCaseCallback<Boolean>() {
            @Override
            public void onResult(Boolean data) {
                mView.onDepartmentDeleted(uuid);
            }

            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }

}
