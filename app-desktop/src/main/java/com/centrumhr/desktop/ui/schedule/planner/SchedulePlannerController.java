package com.centrumhr.desktop.ui.schedule.planner;

import com.centrumhr.data.model.Employee;
import com.centrumhr.data.model.attendance.AttendanceEmployee;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.attendance.AttendancePlanFactory;
import com.centrumhr.desktop.core.Controller;
import com.centrumhr.desktop.ui.schedule.data.AttendanceEmployeeVM;
import com.centrumhr.desktop.ui.schedule.planner.adapter.PlannerTableAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class SchedulePlannerController extends Controller {

    @FXML PlannerMenuComponent plannerMenuController;
    @FXML TableView<AttendanceEmployeeVM> scheduleTable;

    private PlannerTableAdapter mPlannerTableAdapter;

    public SchedulePlannerController() {
        super("layout/schedule_planner_controller.fxml");
    }

    @Override
    public void initialize() {

        mPlannerTableAdapter = new PlannerTableAdapter(scheduleTable);

        List<AttendanceEmployee> attendanceEmployees = new ArrayList<>();

        List<Employee> employees = new ArrayList<>();


        Employee employee1 = new Employee( "Seweryn" , "Sleczka" , "aaa" );
        Employee employee2 = new Employee( "Jan" , "Kowalski" , "bbb" );
        Employee employee3 = new Employee( "John" , "Rumbo" , "ccc" );
        Employee employee4 = new Employee( "Piotr" , "Nowak" , "ddd" );

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);

        AttendancePlanFactory attendancePlanFactory = new AttendancePlanFactory();
        AttendancePlan attendancePlan = attendancePlanFactory.createPlan( "Plan1" , employees , new Date() );


        mPlannerTableAdapter.setData( attendancePlan.getEmployees() );
    }
}
