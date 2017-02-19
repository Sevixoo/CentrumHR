package com.centrumhr.desktop.ui.schedule.list;

import com.centrumhr.application.core.Presenter;
import com.centrumhr.application.core.UI;
import com.centrumhr.application.core.UseCaseThreadExecutor;
import com.centrumhr.application.shedule.LoadScheduleListUseCase;
import com.centrumhr.desktop.ui.schedule.data.ScheduleVM;
import rx.Scheduler;
import rx.schedulers.JavaFxScheduler;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * Created by Seweryn on 19.02.2017.
 */
public class ScheduleListPresenter extends Presenter<ScheduleListPresenter.View> {

    public interface View extends UI{
        void displayScheduleList(List<ScheduleVM> data);
    }

    private LoadScheduleListUseCase loadScheduleListUseCase;

    @Inject
    public ScheduleListPresenter( LoadScheduleListUseCase loadScheduleListUseCase ) {
        super(JavaFxScheduler.getInstance(), UseCaseThreadExecutor.INSTANCE);
        this.loadScheduleListUseCase = loadScheduleListUseCase;
    }

    public void loadScheduleList(){
        executeUseCase(loadScheduleListUseCase,attendancePlanDTOs -> {
                List<ScheduleVM> data = attendancePlanDTOs.stream()
                        .map(ScheduleVM::new)
                        .collect(Collectors.toList());
                view.displayScheduleList(data);
        },view::displayError);
    }

}
