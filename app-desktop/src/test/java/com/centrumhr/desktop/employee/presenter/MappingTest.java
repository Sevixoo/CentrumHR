package com.centrumhr.desktop.employee.presenter;

import com.centrumhr.data.mapper.attendance.AttendancePlanMapper;
import com.centrumhr.data.mapper.employment.EmployeeMapper;
import com.centrumhr.data.model.attendance.AttendancePlanEntity;
import com.centrumhr.data.model.employment.EmployeeEntity;
import com.centrumhr.data.model.employment.EmployeeFactory;
import com.centrumhr.desktop.ui.employee.data.EmployeeVM;
import com.centrumhr.domain.attendance.AttendanceDay;
import com.centrumhr.domain.attendance.AttendanceEmployee;
import com.centrumhr.domain.attendance.AttendancePlan;
import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.dto.attendance.AttendancePlanDTO;
import com.centrumhr.dto.employment.EmployeeDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Seweryn on 28.05.2017.
 */
//com.centrumhr.desktop.employee.presenter
@RunWith(MockitoJUnitRunner.class)
public class MappingTest {

    private static final int N = 100;

    @Test
    public void testAttendancePlanMapping() {
        List<AttendancePlan> list = createListOnAttendancePlan();
        long time = System.currentTimeMillis();
        List<AttendancePlanDTO> employeeDto = list.stream()
                .map(AttendancePlan::convert)
                .collect(Collectors.toList());
        List<AttendancePlanEntity> entities =
                AttendancePlanMapper.INSTANCE.forward(employeeDto);
        System.out.println("dTime=" + (System.currentTimeMillis() - time) + "ms");
        Assert.assertNotNull(entities);
    }
    /*...*/

    /*@Test
    public void testEmployeeMapping(){
        List<EmployeeEntity> list = new ArrayList<>();
        for(int i = 0 ; i < N ; i ++){
            list.add(new EmployeeEntity("name"+i,"lastname"+i,"code"+i));
        }
        for(int i = 0 ; i < 10 ; i ++) {
            long time = System.currentTimeMillis();
            List<EmployeeDTO> employeeDto = EmployeeMapper.INSTANCE.backward(list);
            List<EmployeeVM> employeeVM = employeeDto.stream()
                    .map(EmployeeVM::new)
                    .collect(Collectors.toList());
            System.out.println("dTime=" + (System.currentTimeMillis() - time) + "ms");
            Assert.assertNotNull(employeeVM);
        }
    }*/

    private List<AttendancePlan> createListOnAttendancePlan(){
        AttendancePlanFactory factory = new AttendancePlanFactory(
                new ArrayList<>(),
                new ArrayList<>());

        List<AttendancePlan> data = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            String name = "name" + i;
            Date month = new Date();
            AttendanceEmployee[] employees = new AttendanceEmployee[10];
            AttendanceDay[][] days = new AttendanceDay[10][];
            for(int x = 0 ; x < 10 ; x++){
                employees[x] = new AttendanceEmployee( UUID.randomUUID(),"name" + x , "code" + x );
                days[x] = new AttendanceDay[30];
                for(int y = 0 ; y < 30 ; y++){
                    days[x][y] = new AttendanceDay(new Date(),UUID.randomUUID());
                }
            }
            data.add(factory.createNewPlan(name,
                    month,employees,days));
        }
        return data;
    }

}
