package com.centrumhr.desktop.di;

import com.centrumhr.application.account.IAccountService;
import com.centrumhr.application.account.ILoginService;
import com.centrumhr.application.shedule.CalendarService;
import com.centrumhr.application.sync.IORMLiteDataBaseService;
import com.centrumhr.application.sync.IXMLDataBaseService;
import com.centrumhr.desktop.service.AccountService;
import com.centrumhr.desktop.service.ORMLiteDataBaseService;
import com.centrumhr.desktop.service.LoginService;
import com.centrumhr.desktop.service.XMLDataBaseService;
import com.centrumhr.desktop.service.attendance.HistoryService;
import com.centrumhr.desktop.service.attendance.ProfileService;
import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.domain.attendance.IAttendanceHistoryService;
import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.attendance.IProfileService;
import com.centrumhr.domain.attendance.validation.DayValidator;
import com.centrumhr.domain.attendance.validation.EmployeeValidator;
import com.centrumhr.domain.attendance.validation.SundayWorkingRule;
import com.centrumhr.domain.attendance.validation.PregnantCantWorkMoreThan4HoursValidationRule;
import dagger.Module;
import dagger.Provides;
import javafx.application.Application;
import rx.Scheduler;
import rx.schedulers.JavaFxScheduler;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seweryn on 18.09.2016.
 */
@Module
public class ApplicationModule{

    private Application mContext;

    public ApplicationModule(Application context) {
        this.mContext = context;
        System.out.println(Thread.currentThread().getName());
    }

    @Provides
    public IAccountService provideAccountService() {
        return new AccountService();
    }

    @Provides
    public ILoginService provideLoginService() {
        return new LoginService();
    }

    @Provides @Singleton
    public IORMLiteDataBaseService provideIDataBaseService() {
        return new ORMLiteDataBaseService();
    }

    @Provides @Singleton
    public IXMLDataBaseService provideXMLDataBaseService(){
        return new XMLDataBaseService();
    }

    @Provides
    public ICalendarService providesCalendarService(){
        return new CalendarService();
    }

    @Provides
    public IProfileService profileService(){
        return new ProfileService();
    }

    @Provides
    public IAttendanceHistoryService historyService(){
        return new HistoryService();
    }

    @Provides
    public Scheduler scheduler(){
        return JavaFxScheduler.getInstance();
    }

    @Provides
    public AttendancePlanFactory providesAttendancePlanFactory(ICalendarService calendarService, IProfileService profileService,IAttendanceHistoryService historyService){
        List<DayValidator> dayValidators = new ArrayList<>();
        List<EmployeeValidator> employeeValidators = new ArrayList<>();

        dayValidators.add(new SundayWorkingRule(calendarService,profileService,historyService));
        dayValidators.add(new PregnantCantWorkMoreThan4HoursValidationRule(profileService));

        return new AttendancePlanFactory( dayValidators , employeeValidators );
    }

}
