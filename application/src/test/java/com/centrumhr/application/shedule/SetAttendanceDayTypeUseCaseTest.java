package com.centrumhr.application.shedule;


import com.centrumhr.application.shedule.data.SetAttendanceDayTypeArgument;
import com.centrumhr.domain.attendance.*;
import com.centrumhr.domain.attendance.validation.SundayWorkingRule;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.domain.schedule.ScheduleService;
import com.centrumhr.dto.attendance.AttendanceType;
import com.centrumhr.dto.common.Hour;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.*;

/**
 * Created by Seweryn on 28.05.2017.
 */
//com.centrumhr.application.shedule
@RunWith(MockitoJUnitRunner.class)
public class SetAttendanceDayTypeUseCaseTest {

    @Mock ScheduleCreatorProvider scheduleCreatorProvider;
    @Mock ScheduleService scheduleService;
    @InjectMocks SetAttendanceDayTypeUseCase subject;

    @Test
    public void testShouldCallScheduleAttendanceDay()throws DomainException{
        AttendancePlan attendancePlan = mock(AttendancePlan.class);
        when(scheduleCreatorProvider.get()).thenReturn(attendancePlan);

        SetAttendanceDayTypeArgument argument = new SetAttendanceDayTypeArgument();
        argument.employeeUniqueId = UUID.randomUUID();
        argument.day = 5;
        argument.attendanceType = AttendanceType.NORMAL_WORK;
        argument.hourFrom = new Hour("08:00");
        argument.hourTo = new Hour("16:00");

        subject.execute(argument);

        verify(scheduleService).scheduleDay(attendancePlan,argument.employeeUniqueId,
                argument.day,argument.attendanceType,argument.hourFrom,argument.hourTo );
    }
}
