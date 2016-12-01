package com.centrumhr.application.application.shedule.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.domain.IAttendancePlanRepository;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.importer.AttendancePlanImporter;
import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.attendance.AttendancePlanFactory;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.model.employment.EmployeeFactory;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 05.11.2016.
 */
public class CreateAttendancePlanUseCase extends UseCase<AttendancePlan> {

    private String          mName;
    private Date            mDate;
    private List<UUID>      mEmployeeUniqueIdList;

    private IEmployeeRepository mEmployeeRepository;
    private AttendancePlanFactory mAttendancePlanFactory;
    private AttendancePlanImporter mAttendancePlanImporter;

    public CreateAttendancePlanUseCase(IExecutor executor, IHandler handler, String mName, Date mDate, List<UUID> mEmployeeUniqueIdList, IEmployeeRepository mEmployeeRepository, AttendancePlanImporter attendancePlanImporter, AttendancePlanFactory mAttendancePlanFactory) {
        super(executor, handler);
        this.mName = mName;
        this.mDate = mDate;
        this.mEmployeeUniqueIdList = mEmployeeUniqueIdList;
        this.mEmployeeRepository = mEmployeeRepository;
        this.mAttendancePlanFactory = mAttendancePlanFactory;
        this.mAttendancePlanImporter = attendancePlanImporter;
    }

    @Override
    public AttendancePlan execute() throws Exception {
        //List<Employee> employees = mEmployeeRepository.list( mEmployeeUniqueIdList );
        List<Employee> employees = mEmployeeRepository.list();
        AttendancePlan attendancePlan = mAttendancePlanFactory.createPlan( mName , employees , mDate );
        mAttendancePlanImporter.importData( attendancePlan );
        return attendancePlan;
    }
}
