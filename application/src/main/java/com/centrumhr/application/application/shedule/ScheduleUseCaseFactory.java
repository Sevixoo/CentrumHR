package com.centrumhr.application.application.shedule;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.shedule.usecase.CreateAttendancePlanUseCase;
import com.centrumhr.application.application.shedule.usecase.LoadAttendancePlanUseCase;
import com.centrumhr.application.application.shedule.usecase.SaveAttendancePlanUseCase;
import com.centrumhr.application.application.shedule.usecase.SetAttendanceDayTypeUseCase;
import com.centrumhr.data.domain.IAttendancePlanRepository;
import com.centrumhr.data.domain.IEmployeeRepository;
import com.centrumhr.data.importer.AttendancePlanImporter;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.attendance.AttendancePlanFactory;
import com.centrumhr.data.model.attendance.AttendanceType;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 18.10.2016.
 */
public class ScheduleUseCaseFactory {

    private IExecutor                   mExecutor;
    private IHandler                    mHandler;
    private AttendancePlanFactory       mAttendancePlanFactory;
    private IEmployeeRepository         mEmployeeRepository;
    private IAttendancePlanRepository   mAttendancePlanRepository;
    private AttendancePlanImporter      mAttendancePlanImporter;

    @Inject
    public ScheduleUseCaseFactory(IExecutor mExecutor, IHandler mHandler,AttendancePlanImporter attendancePlanImporter, AttendancePlanFactory attendancePlanFactory , IEmployeeRepository employeeRepository, IAttendancePlanRepository attendancePlanRepository) {
        this.mExecutor = mExecutor;
        this.mHandler = mHandler;
        this.mAttendancePlanFactory = attendancePlanFactory;
        this.mEmployeeRepository = employeeRepository;
        this.mAttendancePlanRepository = attendancePlanRepository;
        this.mAttendancePlanImporter = attendancePlanImporter;
    }

    public SetAttendanceDayTypeUseCase createSetAttendanceDayTypeUseCase(AttendancePlan attendancePlan , UUID employeeUniqueId, UUID  attendanceDayUniqueId , AttendanceType attendanceType ){
        return new SetAttendanceDayTypeUseCase( mExecutor , mHandler , attendancePlan , employeeUniqueId , attendanceDayUniqueId ,  attendanceType );
    }

    public CreateAttendancePlanUseCase createCreateAttendancePlanUseCase(String name , Date beginDate , List<UUID> employeeUniqueIdList ){
        return new CreateAttendancePlanUseCase( mExecutor , mHandler , name , beginDate , employeeUniqueIdList , mEmployeeRepository , mAttendancePlanImporter , mAttendancePlanFactory );
    }

    public LoadAttendancePlanUseCase createLoadAttendancePlanUseCase( UUID uuid ){
        return new LoadAttendancePlanUseCase( mExecutor , mHandler , mAttendancePlanRepository , uuid );
    }

    public SaveAttendancePlanUseCase createSaveAttendancePlanUseCase( AttendancePlan attendancePlan ){
        return new SaveAttendancePlanUseCase( mExecutor , mHandler , attendancePlan , mAttendancePlanImporter );
    }

}
