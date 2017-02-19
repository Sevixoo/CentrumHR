package com.centrumhr.desktop.di;

import com.centrumhr.application.sync.IDataBaseService;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.data.importer.AttendancePlanImporter;
import com.centrumhr.data.importer.EmployeeImporter;
import com.centrumhr.data.model.employment.IEmployeeRepository;
import com.centrumhr.data.repository.AttendancePlanRepository;
import com.centrumhr.data.repository.EmployeeRepository;
import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.schedule.ScheduleService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 18.09.2016.
 */
@Module
public class DataBaseModule {

    IDataBaseService dataBaseService;

    public DataBaseModule(IDataBaseService dataBaseService){
        this.dataBaseService = dataBaseService;
    }

    @Provides
    public IORMLiteDataBase providesORMLiteDataBase(){
        return dataBaseService.provideDataBase();
    }

    @Provides
    public IAttendancePlanRepository providesAttendancePlanRepository(IORMLiteDataBase dataBase, AttendancePlanFactory attendancePlanFactory){
        return new AttendancePlanRepository(dataBase,attendancePlanFactory);
    }

    @Provides
    public IEmployeeRepository providesEmployeeRepository(IORMLiteDataBase dataBase){
        return new EmployeeRepository(dataBase);
    }

    @Provides
    public ScheduleService providesScheduleService( AttendancePlanFactory factory, ICalendarService calendarService){
        return new ScheduleService(factory,calendarService);
    }

}
