package com.centrumhr.desktop.employee.presenter;

import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.employee.LoadEmployeesUseCase;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import com.centrumhr.desktop.ui.employee.presenter.EmployeeListPresenter;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.employment.EmployeeDTO;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

/**
 * Created by Seweryn on 28.05.2017.
 */
//com.centrumhr.desktop.employee.presenter
@RunWith(MockitoJUnitRunner.class)
public class EmployeeListPresenterTest {

    @Mock EmployeeListPresenter.View        view;
    @Mock LoadEmployeesUseCase              loadEmployeesUseCase;
    @InjectMocks EmployeeListPresenter      subject;

    @Before
    public void setup(){
        subject = new EmployeeListPresenter(
                Schedulers.from(new ThreadPoolExecutor(3, 5, 3000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>())),
                loadEmployeesUseCase
        );
    }

    @Test
    public void testShouldDisplayEmployeeListWhenLoaded() throws DomainException {
        subject.setView(view);
        subject.loadEmployees();

        waitSec(() -> {
            verify(view).showProgress();
            verify(view).displayEmployeeList(new ArrayList<>());
            verify(view).hideProgress();
        });
    }

    private void waitSec(Runnable runnable){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnable.run();
    }

}
