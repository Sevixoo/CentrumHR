package com.centrumhr.desktop.di;

import com.centrumhr.application.shedule.CalendarService;
import com.centrumhr.data.model.employment.EmployeeFactory;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seweryn on 25.10.2016.
 */
@Module
public class EmploymentModule {

    public EmploymentModule() { }


}
