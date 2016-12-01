package com.centrumhr.application.presenter;

import com.centrumhr.application.application.common.Message;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.application.application.common.UseCaseCallback;
import com.centrumhr.application.application.department.DepartmentUseCaseFactory;
import com.centrumhr.application.application.employee.EmployeeUseCaseFactory;
import com.centrumhr.application.application.employee.dto.PersonalDataDTO;
import com.centrumhr.application.application.workFunction.WorkFunctionUseCaseFactory;
import com.centrumhr.data.model.employment.Department;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.model.employment.WorkFunction;
import com.centrumhr.data.model.employment.WorkTime;

import javax.inject.Inject;
import java.util.Date;
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
        void departmentLoaded( List<Department> data );
        void workFunctionsLoaded( List<WorkFunction> data  );
    }

    private EmployeeUseCaseFactory  mEmployeeUseCaseFactory;
    private DepartmentUseCaseFactory mDepartmentUseCaseFactory;
    private WorkFunctionUseCaseFactory mWorkFunctionUseCaseFactory;
    private UseCase                 mPendingUseCase;
    private View                    mView;

    @Inject
    public AddEmployeePresenter(EmployeeUseCaseFactory mEmployeeUseCaseFactory , DepartmentUseCaseFactory departmentUseCaseFactory, WorkFunctionUseCaseFactory workFunctionUseCaseFactory) {
        this.mEmployeeUseCaseFactory = mEmployeeUseCaseFactory;
        this.mDepartmentUseCaseFactory = departmentUseCaseFactory;
        this.mWorkFunctionUseCaseFactory = workFunctionUseCaseFactory;
    }

    public void setView( View view ){
        mView = view;
        if(view==null && mPendingUseCase!=null){
            mPendingUseCase.unsubscribe();
        }
    }

    public void loadWorkFunctions(){
        mPendingUseCase = mWorkFunctionUseCaseFactory.createLoadWorkFunctionsUseCase().execute(new UseCaseCallback<List<WorkFunction>>() {
            @Override
            public void onResult(List<WorkFunction> data) {
                mView.workFunctionsLoaded(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }

    public void loadDepartments(){
        mPendingUseCase = mDepartmentUseCaseFactory.createLoadDepartmentsUseCase().execute(new UseCaseCallback<List<Department>>() {
            @Override
            public void onResult(List<Department> data) {
                mView.departmentLoaded(data);
            }

            @Override
            public void onError(Throwable ex) {
                mView.displayError(ex.getMessage());
            }
        });
    }

    public void addEmployee(PersonalDataDTO personal, WorkFunction workFunction, List<Department> departments, Date employmentDate , boolean isJudgment , WorkTime workTime){
        mView.showProgress(Message.LOADING);
        mPendingUseCase = mEmployeeUseCaseFactory.createAddEmployeeUseCase(personal,workFunction,departments,employmentDate,isJudgment,workTime).execute(new UseCaseCallback<Employee>() {
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

    public void editEmployee(PersonalDataDTO personal, WorkFunction workFunction, List<Department> departments, Date employmentDate , boolean isJudgment , WorkTime workTime, Employee employee){
        mView.showProgress(Message.LOADING);
        mPendingUseCase = mEmployeeUseCaseFactory.createEditEmployeeUseCase(personal,workFunction,departments,employmentDate,isJudgment,workTime,employee).execute(new UseCaseCallback<Employee>() {
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
