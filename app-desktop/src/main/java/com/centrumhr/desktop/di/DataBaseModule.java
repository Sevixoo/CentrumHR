package com.centrumhr.desktop.di;

import com.centrumhr.application.sync.IORMLiteDataBaseService;
import com.centrumhr.application.sync.IXMLDataBaseService;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.model.employment.IEmployeeRepository;
import com.centrumhr.data.repository.AttendancePlanRepository;
import com.centrumhr.data.repository.AttendancePlanXMLRepository;
import com.centrumhr.data.repository.EmployeeORMLiteRepository;
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

    private IORMLiteDataBaseService     dataBaseService;
    private IXMLDataBaseService         xmlDataBaseService;

    public DataBaseModule(IORMLiteDataBaseService dataBaseService, IXMLDataBaseService xmlDataBaseService) {
        this.dataBaseService = dataBaseService;
        this.xmlDataBaseService = xmlDataBaseService;
    }

    /*@Provides
    public IAttendancePlanRepository providesAttendancePlanRepository(AttendancePlanFactory attendancePlanFactory){
        return new AttendancePlanRepository(
                dataBaseService.provideDataBase(),
                attendancePlanFactory
        );
    }*/

    @Provides
    public IAttendancePlanRepository providesAttendancePlanRepository(AttendancePlanFactory attendancePlanFactory) {
        return new AttendancePlanXMLRepository(
                xmlDataBaseService.provideDataBase(),
                attendancePlanFactory
        );
    }

    @Provides
    public IORMLiteDataBase providesORMLiteDataBase(){
        return dataBaseService.provideDataBase();
    }

    @Provides
    public IEmployeeRepository providesEmployeeRepository(IORMLiteDataBase dataBase){
        return new EmployeeORMLiteRepository(dataBase);
    }

    @Provides
    public ScheduleService providesScheduleService( AttendancePlanFactory factory, ICalendarService calendarService){
        return new ScheduleService(factory,calendarService);
    }

}
