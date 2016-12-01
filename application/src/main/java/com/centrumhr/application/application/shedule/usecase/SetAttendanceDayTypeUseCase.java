package com.centrumhr.application.application.shedule.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.model.attendance.*;

import java.util.UUID;

/**
 * Created by Seweryn on 18.10.2016.
 */
public class SetAttendanceDayTypeUseCase extends UseCase<AttendanceDay> {

    public AttendancePlan attendancePlan;
    public UUID employeeUniqueId;
    public UUID attendanceDayUniqueId;
    public AttendanceType attendanceType;


    public SetAttendanceDayTypeUseCase(IExecutor executor, IHandler handler, AttendancePlan attendancePlan , UUID employeeUniqueId, UUID  attendanceDayUniqueId , AttendanceType attendanceType ) {
        super(executor, handler);
        this.attendancePlan = attendancePlan;
        this.employeeUniqueId= employeeUniqueId;
        this.attendanceDayUniqueId = attendanceDayUniqueId;
        this.attendanceType = attendanceType;
    }

    @Override
    public AttendanceDay execute() throws Exception {

        for (AttendanceEmployee attendanceEmployee : attendancePlan.getEmployees()) {
            if( attendanceEmployee.getUniqueId().equals( employeeUniqueId ) ){
                for (AttendanceDay attendanceDay : attendanceEmployee.getAttendanceDays()) {
                    if(attendanceDay.getUniqueId().equals(attendanceDayUniqueId)){
                        attendanceDay.setPlanType( attendanceType , new Hour("8:00") , new Hour("16:00"));
                        return attendanceDay;
                    }
                }
            }
        }

        return null;
    }
}
