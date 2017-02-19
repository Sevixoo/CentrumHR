package com.centrumhr.application.shedule;

import com.centrumhr.domain.attendance.AttendancePlan;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class ScheduleCreatorProvider {

    private AttendancePlan attendancePlan;

    @Inject
    public ScheduleCreatorProvider() { }

    public void set(AttendancePlan attendancePlan){
        this.attendancePlan = attendancePlan;
    }

    public AttendancePlan get(){
        return this.attendancePlan;
    }

    public void clear(){
        this.attendancePlan = null;
    }

}
