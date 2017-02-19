package com.centrumhr.desktop.ui.schedule.list;

import com.centrumhr.desktop.CentrumHRApplication;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.data.ScheduleVM;
import com.centrumhr.desktop.ui.schedule.list.adapter.ScheduleTableAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Seweryn on 19.02.2017.
 */
public class ScheduleListController extends Controller implements ScheduleListPresenter.View , ScheduleTableAdapter.Callback{

    public interface Callback{
        void onSelectItem( ScheduleVM scheduleVM );
    }

    private Callback listener;

    @FXML TableView<ScheduleVM> scheduleTableView;

    @Inject ScheduleListPresenter presenter;

    private ScheduleTableAdapter scheduleTableAdapter;

    public ScheduleListController() {
        super("layout/schedule_list_scene.fxml");
    }

    @Override
    public void initialize() {
        CentrumHRApplication.getInstance().getLoggedAccountComponent().inject(this);
        presenter.setView(this);
        scheduleTableAdapter = new ScheduleTableAdapter(scheduleTableView);
        scheduleTableAdapter.setListener(this);
        presenter.loadScheduleList();
    }

    @Override
    public void onSelectItem(ScheduleVM scheduleVM) {
        if(listener!=null){
            listener.onSelectItem(scheduleVM);
        }
    }

    public void setListener(Callback mListener) {
        this.listener = mListener;
    }

    @Override
    public void displayScheduleList(List<ScheduleVM> data) {
        scheduleTableAdapter.setData(data);
    }
}
