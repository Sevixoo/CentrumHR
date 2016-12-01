package com.centrumhr.desktop.di;

import com.centrumhr.data.model.attendance.AttendancePlanFactory;
import com.centrumhr.data.model.employment.EmployeeFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 25.10.2016.
 */
@Module
public class EmploymentModule {

    public EmploymentModule() { }

    @Provides
    public EmployeeFactory provideEmployeeFactory(){
        return new EmployeeFactory();
    }

    @Provides
    public AttendancePlanFactory provideAttendancePlanFactory(){
        return new AttendancePlanFactory();
    }

}
