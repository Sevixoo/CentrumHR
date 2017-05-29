package com.centrumhr.data.importer;

import com.centrumhr.data.core.ormlite.DAO;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.core.DatabaseException;
import com.centrumhr.data.model.attendance.AttendanceDayEntity;
import com.centrumhr.data.model.attendance.AttendanceEmployeeEntity;
import com.centrumhr.data.model.attendance.AttendancePlanEntity;

import javax.inject.Inject;

/**
 * Created by Seweryn on 13.11.2016.
 */
public class AttendancePlanImporter {

    private DAO<AttendanceEmployeeEntity>   attendanceEmployeeDAO;
    private DAO<AttendanceDayEntity>        attendanceDayDAO;
    private DAO<AttendancePlanEntity>       attendancePlanDAO;

    @Inject
    public AttendancePlanImporter(IORMLiteDataBase dataBase) {
        this.attendanceEmployeeDAO = dataBase.provideDAO(AttendanceEmployeeEntity.class);
        this.attendancePlanDAO = dataBase.provideDAO(AttendancePlanEntity.class);
        this.attendanceDayDAO = dataBase.provideDAO(AttendanceDayEntity.class);
    }

    public void importData(AttendancePlanEntity attendancePlanEntityDTO)throws DatabaseException {

        AttendancePlanEntity attendancePlanEntity = attendancePlanDAO.load(attendancePlanEntityDTO.getUniqueId());
        if(attendancePlanEntity == null){
            attendancePlanEntity = attendancePlanEntityDTO;
        }else{
            attendancePlanEntity.update(attendancePlanEntityDTO);
        }

        attendancePlanDAO.save(attendancePlanEntity);

        for( AttendanceEmployeeEntity attendanceEmployeeEntity : attendancePlanEntity.getEmployees() ){
            boolean found = false;
            for( AttendanceEmployeeEntity attendanceEmployeeEntityDTO : attendancePlanEntityDTO.getEmployees() ){
                if ( attendanceEmployeeEntity.equals(attendanceEmployeeEntityDTO) ){
                    found = true;
                    break;
                }
            }
            if(!found){
                for(AttendanceDayEntity attendanceDayEntity : attendanceEmployeeEntity.getAttendanceDayEntities()){
                    attendanceDayDAO.delete(attendanceDayEntity.getUniqueId());
                }
                attendanceDayDAO.delete(attendanceEmployeeEntity.getUniqueId());
            }
        }

        for(AttendanceEmployeeEntity attendanceEmployeeEntityDTO : attendancePlanEntityDTO.getEmployees()){
            AttendanceEmployeeEntity attendanceEmployeeEntity = attendanceEmployeeDAO.load( attendanceEmployeeEntityDTO.getUniqueId() );
            if(attendanceEmployeeEntity ==null){
                attendanceEmployeeEntity = attendanceEmployeeEntityDTO;
            }else{
                attendanceEmployeeEntity.update(attendanceEmployeeEntityDTO);
            }
            attendanceEmployeeEntity.setAttendancePlanEntity(attendancePlanEntity);
            attendanceEmployeeDAO.save(attendanceEmployeeEntity);

            for( AttendanceDayEntity attendanceDayEntity : attendanceEmployeeEntity.getAttendanceDayEntities() ){
                boolean found = false;
                for( AttendanceDayEntity attendanceDayEntityDTO : attendanceEmployeeEntityDTO.getAttendanceDayEntities() ){
                    if ( attendanceDayEntityDTO.equals(attendanceDayEntity) ){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    attendanceEmployeeDAO.delete(attendanceDayEntity.getUniqueId());
                }
            }

            for(AttendanceDayEntity attendanceDayEntityDTO : attendanceEmployeeEntityDTO.getAttendanceDayEntities()){
                AttendanceDayEntity attendanceDayEntity = attendanceDayDAO.load(attendanceDayEntityDTO.getUniqueId());
                if(attendanceDayEntity ==null){
                    attendanceDayEntity = attendanceDayEntityDTO;
                }else{
                    attendanceDayEntity.update(attendanceDayEntityDTO);
                }
                attendanceDayEntity.setEmployee(attendanceEmployeeEntity);
                attendanceDayDAO.save(attendanceDayEntity);
            }

        }


    }

}
