package com.centrumhr.domain.attendance;

import com.centrumhr.domain.attendance.validation.SundayWorkingRule;
import com.centrumhr.domain.common.DateUtils;
import com.centrumhr.dto.attendance.AttendanceType;
import com.centrumhr.dto.common.Hour;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.*;

/**
 * Created by Seweryn on 28.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SundayWorkingRuleTest {

    private SundayWorkingRule           subject;
    private AttendanceEmployee          employee;

    @Before
    public void setup(){
        employee = new AttendanceEmployee(MockHistoryService.EMPLOYEE_THAT_WORKED_LAST_SUNDAY,"","");
        subject = new SundayWorkingRule(
                new MockCalendarService(),
                new MockProfileService(),
                new MockHistoryService()
        );
    }

    @Test
    public void testShouldBeInvalidWhenWorksAllWeekAndWorkedPastSaturday(){
        AttendanceDay[] days = generateDaysForMonth("01.05.2017");
        employee = new AttendanceEmployee(MockHistoryService.EMPLOYEE_THAT_WORKED_LAST_SUNDAY,"","");
        days[0].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[1].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[2].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[3].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[4].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[5].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[6].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        Assert.assertTrue( !subject.isValid(employee,days,4) );
    }

    @Test
    public void testShouldBeInvalidWhenWorksAllWeekAndWorkedPastSaturday2(){
        AttendanceDay[] days = generateDaysForMonth("01.05.2017");
        employee = new AttendanceEmployee(MockHistoryService.EMPLOYEE_THAT_WORKED_LAST_SUNDAY,"","");
        days[0].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[1].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[2].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[3].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[4].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[5].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[6].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        Assert.assertTrue( !subject.isValid(employee,days,0) );
        Assert.assertTrue( !subject.isValid(employee,days,1) );
        Assert.assertTrue( !subject.isValid(employee,days,2) );
        Assert.assertTrue( !subject.isValid(employee,days,3) );
        Assert.assertTrue( !subject.isValid(employee,days,4) );
        Assert.assertTrue( !subject.isValid(employee,days,5) );
        Assert.assertTrue( subject.isValid(employee,days,6) );
    }

    @Test
    public void testShouldBeValidWhenWorksNotScheduled(){
        AttendanceDay[] days = generateDaysForMonth("01.05.2017");
        Assert.assertTrue( subject.isValid(employee,days,0) );
        Assert.assertTrue( subject.isValid(employee,days,1) );
        Assert.assertTrue( subject.isValid(employee,days,2) );
        Assert.assertTrue( subject.isValid(employee,days,3) );
        Assert.assertTrue( subject.isValid(employee,days,4) );
        Assert.assertTrue( subject.isValid(employee,days,5) );
        Assert.assertTrue( subject.isValid(employee,days,6) );
    }

    @Test
    public void testShouldBeInvalidWhenWorksAllWeekAndWorkedLastSaturday(){
        AttendanceDay[] days = generateDaysForMonth("01.05.2017");
        employee = new AttendanceEmployee(MockHistoryService.EMPLOYEE_THAT_NOT_WORKED_LAST_SUNDAY,"","");
        days[14].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[15].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[16].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[17].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[18].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[19].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[6].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));//last saturday
        Assert.assertTrue( subject.isValid(employee,days,12) );
        Assert.assertTrue( !subject.isValid(employee,days,13) );
        Assert.assertTrue( !subject.isValid(employee,days,14) );
        Assert.assertTrue( !subject.isValid(employee,days,15) );
        Assert.assertTrue( !subject.isValid(employee,days,16) );
        Assert.assertTrue( !subject.isValid(employee,days,17) );
        Assert.assertTrue( !subject.isValid(employee,days,18) );
        Assert.assertTrue( !subject.isValid(employee,days,19) );
        Assert.assertTrue( subject.isValid(employee,days,20) );
    }

    @Test
    public void testShouldBeValidWhenWorksAllWeekAndWorkedLastSaturday(){
        AttendanceDay[] days = generateDaysForMonth("01.05.2017");
        employee = new AttendanceEmployee(MockHistoryService.EMPLOYEE_THAT_NOT_WORKED_LAST_SUNDAY,"","");
        days[14].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[15].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[16].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[17].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[18].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        days[19].schedule(AttendanceType.NORMAL_WORK,new Hour("8:00"),new Hour("16:00"));
        Assert.assertTrue( subject.isValid(employee,days,12) );
        Assert.assertTrue( subject.isValid(employee,days,13) );
        Assert.assertTrue( subject.isValid(employee,days,14) );
        Assert.assertTrue( subject.isValid(employee,days,15) );
        Assert.assertTrue( subject.isValid(employee,days,16) );
        Assert.assertTrue( subject.isValid(employee,days,17) );
        Assert.assertTrue( subject.isValid(employee,days,18) );
        Assert.assertTrue( subject.isValid(employee,days,19) );
        Assert.assertTrue( subject.isValid(employee,days,20) );
    }

    private AttendanceDay[] generateDaysForMonth(String dateString){
        int numOfDaysInMonth = 31;
        AttendanceDay[] days = new AttendanceDay[numOfDaysInMonth];
        for(int i = 1 ; i < numOfDaysInMonth ; i++){
                String dayPrefix = i > 9 ? String.valueOf(i) : "0"+i;
                days[i-1] = new AttendanceDay(DateUtils.create(dayPrefix+dateString.substring(2,dateString.length())), UUID.randomUUID() );
        }
        return days;
    }

}
