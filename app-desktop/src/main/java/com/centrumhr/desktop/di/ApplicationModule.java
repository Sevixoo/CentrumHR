package com.centrumhr.desktop.di;

import com.centrumhr.application.account.IAccountService;
import com.centrumhr.application.account.ILoginService;
import com.centrumhr.application.shedule.CalendarService;
import com.centrumhr.application.sync.IDataBaseService;
import com.centrumhr.desktop.service.AccountService;
import com.centrumhr.desktop.service.DataBaseService;
import com.centrumhr.desktop.service.LoginService;
import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.attendance.validation.DayValidator;
import com.centrumhr.domain.attendance.validation.EmployeeValidator;
import com.centrumhr.domain.attendance.validation.FreeForWorkingSundayValidatorRule;
import com.centrumhr.domain.attendance.validation.PregnantCantWorkMoreThan4HoursValidationRule;
import com.centrumhr.domain.schedule.ScheduleService;
import dagger.Module;
import dagger.Provides;
import javafx.application.Application;

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
    public IDataBaseService provideIDataBaseService() {
        return new DataBaseService();
    }


    @Provides
    public ICalendarService providesCalendarService(){
        return new CalendarService();
    }

    @Provides
    public AttendancePlanFactory providesAttendancePlanFactory(){
        List<DayValidator> dayValidators = new ArrayList<>();
        List<EmployeeValidator> employeeValidators = new ArrayList<>();

        dayValidators.add(new FreeForWorkingSundayValidatorRule("Message"));
        dayValidators.add(new PregnantCantWorkMoreThan4HoursValidationRule("Message"));

        return new AttendancePlanFactory( dayValidators , employeeValidators );
    }

}
