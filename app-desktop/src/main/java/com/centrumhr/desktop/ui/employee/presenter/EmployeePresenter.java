package com.centrumhr.desktop.ui.employee.presenter;

import com.centrumhr.application.core.Presenter;
import com.centrumhr.application.core.UI;
import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.Message;
import com.centrumhr.application.employee.RemoveEmployeeUseCase;
import rx.schedulers.JavaFxScheduler;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class EmployeePresenter extends Presenter<EmployeePresenter.View>{

    public interface View extends UI{
        void onUserDeleted();
    }

    private RemoveEmployeeUseCase removeEmployeeUseCase;

    @Inject
    public EmployeePresenter(RemoveEmployeeUseCase removeEmployeeUseCase) {
        super(JavaFxScheduler.getInstance(), UseCaseThreadExecutor.INSTANCE);
        this.removeEmployeeUseCase = removeEmployeeUseCase;
    }

    public void deleteEmployee(UUID uniqueId){
        view.showProgress();
        executeUseCase(removeEmployeeUseCase,uniqueId, result -> {
                view.hideProgress();
                view.onUserDeleted();
            },throwable -> {
                view.hideProgress();
                view.displayError(throwable);
            }
        );
    }

}
