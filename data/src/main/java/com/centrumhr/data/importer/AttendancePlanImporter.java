package com.centrumhr.data.importer;

import com.centrumhr.data.domain.IAttendanceDayRepository;
import com.centrumhr.data.domain.IAttendanceEmployeeRepository;
import com.centrumhr.data.domain.IAttendancePlanRepository;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.model.attendance.AttendanceEmployee;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.employment.Employee;

/**
 * Created by Seweryn on 13.11.2016.
 */
public class AttendancePlanImporter {

    private IAttendanceEmployeeRepository mAttendanceEmployeeRepository;
    private IAttendancePlanRepository mAttendancePlanRepository;
    private IAttendanceDayRepository mAttendanceDayRepository;

    public AttendancePlanImporter(IAttendanceEmployeeRepository mAttendanceEmployeeRepository, IAttendancePlanRepository mAttendancePlanRepository, IAttendanceDayRepository mAttendanceDayRepository) {
        this.mAttendanceEmployeeRepository = mAttendanceEmployeeRepository;
        this.mAttendancePlanRepository = mAttendancePlanRepository;
        this.mAttendanceDayRepository = mAttendanceDayRepository;
    }

    public void importData(AttendancePlan attendancePlanDTO)throws DatabaseException {

        AttendancePlan attendancePlan = mAttendancePlanRepository.load(attendancePlanDTO.getUniqueId());
        if(attendancePlan == null){
            attendancePlan = attendancePlanDTO;
        }else{
            attendancePlan.update(attendancePlanDTO);
        }

        mAttendancePlanRepository.save(attendancePlan);

        for( AttendanceEmployee attendanceEmployee : attendancePlan.getEmployees() ){
            boolean found = false;
            for( AttendanceEmployee attendanceEmployeeDTO : attendancePlanDTO.getEmployees() ){
                if ( attendanceEmployee.equals(attendanceEmployeeDTO) ){
                    found = true;
                    break;
                }
            }
            if(!found){
                for(AttendanceDay attendanceDay : attendanceEmployee.getAttendanceDays()){
                    mAttendanceDayRepository.delete(attendanceDay.getUniqueId());
                }
                mAttendanceEmployeeRepository.delete(attendanceEmployee.getUniqueId());
            }
        }

        for(AttendanceEmployee attendanceEmployeeDTO : attendancePlanDTO.getEmployees()){
            AttendanceEmployee attendanceEmployee = mAttendanceEmployeeRepository.load( attendanceEmployeeDTO.getUniqueId() );
            if(attendanceEmployee==null){
                attendanceEmployee = attendanceEmployeeDTO;
            }else{
                attendanceEmployee.update(attendanceEmployeeDTO);
            }
            attendanceEmployee.setAttendancePlan(attendancePlan);
            mAttendanceEmployeeRepository.save(attendanceEmployee);

            for( AttendanceDay attendanceDay : attendanceEmployee.getAttendanceDays() ){
                boolean found = false;
                for( AttendanceDay attendanceDayDTO : attendanceEmployeeDTO.getAttendanceDays() ){
                    if ( attendanceDayDTO.equals( attendanceDay) ){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    mAttendanceDayRepository.delete(attendanceDay.getUniqueId());
                }
            }

            for(AttendanceDay attendanceDayDTO : attendanceEmployeeDTO.getAttendanceDays()){
                AttendanceDay attendanceDay = mAttendanceDayRepository.load(attendanceDayDTO.getUniqueId());
                if(attendanceDay==null){
                    attendanceDay = attendanceDayDTO;
                }else{
                    attendanceDay.update(attendanceDayDTO);
                }
                attendanceDay.setEmployee(attendanceEmployee);
                mAttendanceDayRepository.save(attendanceDay);
            }

        }


    }

}
