package com.centrumhr.data.mapper.attendance;

import com.centrumhr.data.core.Mapper;
import com.centrumhr.dto.attendance.AttendanceEmployeeDTO;
import com.centrumhr.data.model.attendance.AttendanceEmployeeEntity;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class AttendanceEmployeeMapper extends Mapper<AttendanceEmployeeEntity,AttendanceEmployeeDTO> {

    public static final AttendanceEmployeeMapper INSTANCE = new AttendanceEmployeeMapper();

    @Override
    public AttendanceEmployeeEntity forward(AttendanceEmployeeDTO attendanceEmployeeDTO) {
        if(attendanceEmployeeDTO == null)return null;
        return new AttendanceEmployeeEntity(
                attendanceEmployeeDTO.getUniqueId(),
                attendanceEmployeeDTO.getName(),
                attendanceEmployeeDTO.getCode(),
                attendanceEmployeeDTO.getEmployeeUniqueId()
        );
    }

    @Override
    public AttendanceEmployeeDTO backward(AttendanceEmployeeEntity attendanceEmployeeEntity) {
        if(attendanceEmployeeEntity == null)return null;
        return new AttendanceEmployeeDTO(
                attendanceEmployeeEntity.getUniqueId(),
                attendanceEmployeeEntity.getName(),
                attendanceEmployeeEntity.getCode(),
                attendanceEmployeeEntity.getEmployeeUniqueId()
        );
    }
}
