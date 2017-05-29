package com.centrumhr.domain.attendance;

import com.centrumhr.domain.attendance.validation.DayValidator;
import com.centrumhr.domain.attendance.validation.EmployeeValidator;
import com.centrumhr.domain.schedule.AttendanceEmployeeSummary;
import com.centrumhr.dto.common.Hour;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.attendance.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 07.02.2017.
 */
public class AttendancePlan {

    private UUID                    uniqueId;
    private String                  name;
    private Date                    startDate;
    private Date                    createDate;

    private PlanState               state;
    private AttendanceEmployee[]    attendanceEmployees;
    private AttendanceDay[][]       days;

    private List<DayValidator>      dayValidators;
    private List<EmployeeValidator> employeeValidators;

    AttendancePlan(String name, Date startDate, Date createDate, AttendanceEmployee[] attendanceEmployees, AttendanceDay[][] days,List<DayValidator> dayValidators,List<EmployeeValidator> employeeValidators) {
        this(UUID.randomUUID(),name,startDate,createDate,PlanState.OPEN,attendanceEmployees,days,dayValidators,employeeValidators);
    }

    AttendancePlan(UUID uniqueId, String name, Date startDate, Date createDate, PlanState state, AttendanceEmployee[] attendanceEmployees, AttendanceDay[][] days,List<DayValidator> dayValidators,List<EmployeeValidator> employeeValidators) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.startDate = startDate;
        this.createDate = createDate;
        this.state = state;
        this.attendanceEmployees = attendanceEmployees;
        this.days = days;
        this.dayValidators = dayValidators;
        this.employeeValidators = employeeValidators;
    }

    public AttendanceDayDTO scheduleDay(UUID attendanceEmployeeId , int day , AttendanceType attendanceType , Hour hourFrom , Hour hourTo )throws DomainException{
        if(state==PlanState.CLOSED)throw new DomainException("Plan is closed");
        AttendanceDay attendanceDay = findDay(attendanceEmployeeId,day);
        attendanceDay.schedule( attendanceType , hourFrom , hourTo );
        validateDaysState();
        validateEmployeesState();
        return attendanceDay.convert();
    }

    public void commit()throws DomainException{
        if(validateDaysState() | validateEmployeesState()){
            throw new DomainException("Plan has errors");
        }
        state = PlanState.CLOSED;
    }

    public boolean validateDaysState(){
        boolean hasErrors = false;
        for( int i = 0 ; i < attendanceEmployees.length ; i++ ){
            AttendanceEmployee employee = attendanceEmployees[i];
            for( AttendanceDay attendanceDay : days[i] ){
                attendanceDay.clearErrors();
            }
            for (DayValidator validator: dayValidators ) {
                for(int dayNum = 0 ; dayNum < days[i].length ; dayNum++){
                    if(!validator.isValid(employee,days[i],dayNum)){
                        days[i][dayNum].addError(validator.getErrorMessage());
                        hasErrors = true;
                    }
                }
            }
        }
        return hasErrors;
    }

    public boolean validateEmployeesState(){
        boolean hasErrors = false;
        for( int i = 0 ; i < attendanceEmployees.length ; i++ ){
            AttendanceEmployee employee = attendanceEmployees[i];
            employee.clearErrors();
            for (EmployeeValidator validator: employeeValidators ) {
                if(!validator.isValid(employee,days[i])){
                    employee.addError(validator.getErrorMessage());
                    hasErrors = true;
                }
            }
        }
        return hasErrors;
    }

    private AttendanceDay findDay(UUID attendanceEmployeeId, int day) throws DomainException {
        for (int i = 0; i < attendanceEmployees.length; i++) {
            if(attendanceEmployees[i].uniqueId().equals(attendanceEmployeeId)){
                return days[i][day];
            }
        }
        throw new DomainException("Employee not found");
    }

    private AttendanceEmployee findEmployee(UUID attendanceEmployeeId)throws DomainException{
        for (int i = 0; i < attendanceEmployees.length; i++) {
            if(attendanceEmployees[i].uniqueId().equals(attendanceEmployeeId)){
                return attendanceEmployees[i];
            }
        }
        throw new DomainException("Employee not found");
    }


    public int countWorkingDays(UUID attendanceEmployeeId)throws DomainException{
        for (int i = 0; i < attendanceEmployees.length; i++) {
            if(attendanceEmployees[i].uniqueId().equals(attendanceEmployeeId)){
                int sum = 0;
                for (int j = 0; j < days[i].length; j++) {
                    AttendanceDay attendanceDay = days[i][j];
                    sum += attendanceDay.planedDuration();
                }
                return sum;
            }
        }
        throw new DomainException("Employee not found");
    }

    public AttendancePlanDTO convert() {
        AttendanceEmployeeDTO[] employeesDTO = new AttendanceEmployeeDTO[attendanceEmployees.length];
        for (int i = 0; i < attendanceEmployees.length; i++) {
            employeesDTO[i] = attendanceEmployees[i].convert();
        }
        AttendanceDayDTO[][] daysDTO = new AttendanceDayDTO[days.length][];
        for (int i = 0; i < days.length; i++) {
            daysDTO[i] = new AttendanceDayDTO[days[i].length];
            for (int j = 0; j < days[i].length; j++) {
                daysDTO[i][j] = days[i][j].convert();
            }
        }
        return new AttendancePlanDTO( uniqueId , name , startDate , createDate , state , employeesDTO , daysDTO );
    }

    public void setName(String name) {
        this.name = name;
    }

    public int maxWorkingHours(UUID attendanceEmployeeId) throws DomainException{
        return findEmployee(attendanceEmployeeId).maxWorkingHours();
    }

    private AttendanceEmployeeSummary createSummary(int employeePos){
        AttendanceEmployeeSummary summary = new AttendanceEmployeeSummary();
        summary.maxWorkingHours = attendanceEmployees[employeePos].maxWorkingHours();
        summary.workingHours = 0;
        for (int j = 0; j < days[employeePos].length; j++) {
            AttendanceDay attendanceDay = days[employeePos][j];
            summary.workingHours += attendanceDay.planedDuration();
        }
        return summary;
    }

    public HashMap<UUID,AttendanceEmployeeSummary> employeeSummary() {
        HashMap<UUID,AttendanceEmployeeSummary> summary = new HashMap<>();
        for (int i = 0; i < attendanceEmployees.length; i++) {
            summary.put(attendanceEmployees[i].getUniqueId(),createSummary(i));
        }
        return summary;
    }
}
