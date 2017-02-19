package com.centrumhr.desktop.ui.employee.presenter;

import com.centrumhr.application.core.Presenter;
import com.centrumhr.application.core.UI;
import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.Message;
import com.centrumhr.application.department.LoadDepartmentsUseCase;
import com.centrumhr.application.employee.AddEmployeeUseCase;
import com.centrumhr.application.employee.EditEmployeeUseCase;
import com.centrumhr.application.employee.GetEmployeeUseCase;
import com.centrumhr.application.employee.data.EmployeeArgument;
import com.centrumhr.application.employee.dto.PersonalDataDTO;
import com.centrumhr.application.workFunction.LoadWorkFunctionsUseCase;
import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.data.model.employment.EmployeeEntity;
import com.centrumhr.data.model.employment.WorkFunctionEntity;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import com.centrumhr.desktop.ui.employee.data.WorkFunctionVM;
import com.centrumhr.desktop.ui.settings.department.data.DepartmentVM;
import com.centrumhr.dto.employment.DepartmentDTO;
import com.centrumhr.dto.employment.EmployeeDTO;
import com.centrumhr.dto.employment.WorkFunctionDTO;
import com.centrumhr.dto.employment.WorkTime;
import rx.Scheduler;
import rx.schedulers.JavaFxScheduler;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AddEmployeePresenter extends Presenter<AddEmployeePresenter.View>{

    public interface View extends UI{
        void displayAddEmployeeSuccess(EmployeeVM employee );
        void dismiss();
        void editEmployee(EmployeeVM employee );
        void departmentLoaded( List<DepartmentVM> data );
        void workFunctionsLoaded( List<WorkFunctionVM> data  );
    }

    private LoadWorkFunctionsUseCase loadWorkFunctionsUseCase;
    private LoadDepartmentsUseCase   loadDepartmentsUseCase;
    private AddEmployeeUseCase       addEmployeeUseCase;
    private GetEmployeeUseCase       getEmployeeUseCase;
    private EditEmployeeUseCase      editEmployeeUseCase;

    @Inject
    public AddEmployeePresenter( LoadWorkFunctionsUseCase loadWorkFunctionsUseCase, LoadDepartmentsUseCase loadDepartmentsUseCase, AddEmployeeUseCase addEmployeeUseCase, GetEmployeeUseCase getEmployeeUseCase, EditEmployeeUseCase editEmployeeUseCase) {
        super(JavaFxScheduler.getInstance(), UseCaseThreadExecutor.INSTANCE);
        this.loadWorkFunctionsUseCase = loadWorkFunctionsUseCase;
        this.loadDepartmentsUseCase = loadDepartmentsUseCase;
        this.addEmployeeUseCase = addEmployeeUseCase;
        this.getEmployeeUseCase = getEmployeeUseCase;
        this.editEmployeeUseCase = editEmployeeUseCase;
    }

    public void loadWorkFunctions(){
        executeUseCase(loadWorkFunctionsUseCase,workFunctionDTOs -> {
                    List<WorkFunctionVM> list = workFunctionDTOs.stream()
                            .map(WorkFunctionVM::new)
                            .collect(Collectors.toList());
                    view.workFunctionsLoaded(list);
                },
                view::displayError
        );
    }

    public void loadDepartments(){
        executeUseCase(loadDepartmentsUseCase, departmentDTOs -> {
                    List<DepartmentVM> list = departmentDTOs.stream()
                            .map(DepartmentVM::new)
                            .collect(Collectors.toList());
                    view.departmentLoaded(list);
                },
                view::displayError
        );
    }

    public void addEmployee(PersonalDataDTO personal, WorkFunctionVM workFunction , List<DepartmentVM> departments, Date employmentDate , boolean isJudgment , WorkTime workTime){
        view.showProgress();

        EmployeeArgument employeeArgument = new EmployeeArgument();
        employeeArgument.personalDataDTO = personal;
        employeeArgument.workFunction = new WorkFunctionDTO(workFunction.getUniqueId(),workFunction.getName());
        employeeArgument.departments  = departments.stream().map(departmentVM -> {
            return new DepartmentDTO(
                    departmentVM.getUuid(),
                    departmentVM.getName()
            );
        }).collect(Collectors.toList());
        employeeArgument.isJudgment = isJudgment;
        employeeArgument.workTime = workTime;
        employeeArgument.employmentDate = employmentDate;

        executeUseCase(addEmployeeUseCase,employeeArgument,
                employeeDTO -> {
                    view.hideProgress();
                    view.displayAddEmployeeSuccess(new EmployeeVM(employeeDTO));
                },throwable -> {
                    view.hideProgress();
                    view.displayError(throwable);
                }
        );
    }

    public void loadEmployee(UUID uuid){
        view.showProgress();
        executeUseCase(getEmployeeUseCase,uuid,
                employeeDTO -> {
                    view.hideProgress();
                    view.editEmployee( new EmployeeVM(employeeDTO) );
                },throwable -> {
                    throwable.printStackTrace();
                    view.hideProgress();
                    view.displayError(throwable);
                }
        );
    }

    public void editEmployee( UUID uuid, PersonalDataDTO personal, WorkFunctionVM workFunction , List<DepartmentVM> departments, Date employmentDate , boolean isJudgment , WorkTime workTime ){
        view.showProgress();
        EmployeeArgument employeeArgument = new EmployeeArgument();
        employeeArgument.editingUniqueId = uuid;
        employeeArgument.personalDataDTO = personal;
        employeeArgument.workFunction = new WorkFunctionDTO(workFunction.getUniqueId(),workFunction.getName());
        employeeArgument.departments  = departments.stream().map(departmentVM -> {
            return new DepartmentDTO(
                    departmentVM.getUuid(),
                    departmentVM.getName()
            );
        }).collect(Collectors.toList());
        employeeArgument.isJudgment = isJudgment;
        employeeArgument.workTime = workTime;
        employeeArgument.employmentDate = employmentDate;

        executeUseCase(editEmployeeUseCase,employeeArgument,
                employeeDTO -> {
                    view.hideProgress();
                    view.displayAddEmployeeSuccess(new EmployeeVM(employeeDTO));
                },throwable -> {
                    view.hideProgress();
                    view.displayError(throwable);
                }
        );
    }

}
