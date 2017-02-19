package com.centrumhr.data.mapper.attendance;

import com.centrumhr.data.core.Mapper;
import com.centrumhr.data.model.attendance.AttendanceDayEntity;
import com.centrumhr.data.model.attendance.AttendanceEmployeeEntity;
import com.centrumhr.domain.attendance.AttendanceEmployee;
import com.centrumhr.dto.attendance.AttendanceDayDTO;
import com.centrumhr.dto.attendance.AttendanceEmployeeDTO;
import com.centrumhr.dto.attendance.AttendancePlanDTO;
import com.centrumhr.data.model.attendance.AttendancePlanEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class AttendancePlanMapper extends Mapper<AttendancePlanEntity,AttendancePlanDTO> {

    public static final AttendancePlanMapper INSTANCE = new AttendancePlanMapper();

    @Override
    public AttendancePlanEntity forward(AttendancePlanDTO attendancePlanDTO) {
        if(attendancePlanDTO == null)return null;

        AttendancePlanEntity entity = new AttendancePlanEntity(
                attendancePlanDTO.getUniqueId(),
                attendancePlanDTO.getName(),
                attendancePlanDTO.getStartDate(),
                attendancePlanDTO.getCreateDate(),
                attendancePlanDTO.getState()
        );

        List<AttendanceEmployeeEntity> employeeEntities = new ArrayList<>();
        for (int i = 0; i < attendancePlanDTO.getEmployees().length; i++) {
            AttendanceEmployeeDTO employeeDTO = attendancePlanDTO.getEmployees()[i];
            AttendanceEmployeeEntity employeeEntity = AttendanceEmployeeMapper.INSTANCE.forward(employeeDTO);
            List<AttendanceDayEntity> attendanceDayEntities = new ArrayList<>();
            for (int j = 0; j < attendancePlanDTO.getDays()[i].length; j++) {
                AttendanceDayEntity attendanceDayEntity = AttendanceDayMapper.INSTANCE.forward(attendancePlanDTO.getDays()[i][j]);
                attendanceDayEntity.setEmployee(employeeEntity);
                attendanceDayEntities.add( attendanceDayEntity );
            }
            employeeEntity.setAttendancePlanEntity(entity);
            employeeEntity.setAttendanceDayEntities(attendanceDayEntities);
            employeeEntities.add(employeeEntity);
        }

        entity.setEmployees(employeeEntities);
        return entity;
    }

    @Override
    public AttendancePlanDTO backward(AttendancePlanEntity attendancePlanEntity) {
        if(attendancePlanEntity == null)return null;
        AttendanceEmployeeDTO[] attendanceEmployeeDTOs = null;
        AttendanceDayDTO[][] attendanceDayDTOs = null;
        if(attendancePlanEntity.getEmployees()!=null){
            attendanceEmployeeDTOs = new AttendanceEmployeeDTO[attendancePlanEntity.getEmployees().size()];
            attendanceDayDTOs = new AttendanceDayDTO[attendancePlanEntity.getEmployees().size()][];
            int i = 0;
            for (AttendanceEmployeeEntity employeeEntity : attendancePlanEntity.getEmployees()) {
                attendanceEmployeeDTOs[i] = AttendanceEmployeeMapper.INSTANCE.backward(employeeEntity);
                attendanceDayDTOs[i] = new AttendanceDayDTO[employeeEntity.getAttendanceDayEntities().size()];
                int j = 0;
                for (AttendanceDayEntity dayEntity : employeeEntity.getAttendanceDayEntities()) {
                    attendanceDayDTOs[i][j] = AttendanceDayMapper.INSTANCE.backward(dayEntity);
                    j++;
                }
                i++;
            }
        }
        return new AttendancePlanDTO(
                attendancePlanEntity.getUniqueId(),
                attendancePlanEntity.getName(),
                attendancePlanEntity.getStartDate(),
                attendancePlanEntity.getCreateDate(),
                attendancePlanEntity.getState(),
                attendanceEmployeeDTOs,
                attendanceDayDTOs
        );
    }
}
