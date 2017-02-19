package com.centrumhr.data.mapper.attendance;

import com.centrumhr.data.core.Mapper;
import com.centrumhr.dto.attendance.AttendanceDayDTO;
import com.centrumhr.data.model.attendance.AttendanceDayEntity;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class AttendanceDayMapper extends Mapper<AttendanceDayEntity,AttendanceDayDTO> {

    public static final AttendanceDayMapper INSTANCE = new AttendanceDayMapper();

    @Override
    public AttendanceDayEntity forward(AttendanceDayDTO attendanceDayDTO) {
        if(attendanceDayDTO == null )return null;
        return new AttendanceDayEntity(
                attendanceDayDTO.getUniqueId(),
                attendanceDayDTO.getDate(),
                attendanceDayDTO.getType(),
                attendanceDayDTO.getPlanStartHour(),
                attendanceDayDTO.getPlanEndHour(),
                attendanceDayDTO.getAttendStartHour(),
                attendanceDayDTO.getAttendEndHour(),
                attendanceDayDTO.getDescription()
        );
    }

    @Override
    public AttendanceDayDTO backward(AttendanceDayEntity attendanceDayEntity) {
        if(attendanceDayEntity == null )return null;
        return new AttendanceDayDTO(
                attendanceDayEntity.getUniqueId(),
                attendanceDayEntity.getEmployee().getUniqueId(),
                attendanceDayEntity.getDate(),
                attendanceDayEntity.getType(),
                attendanceDayEntity.getPlanStartHour(),
                attendanceDayEntity.getPlanEndHour(),
                attendanceDayEntity.getAttendStartHour(),
                attendanceDayEntity.getAttendEndHour(),
                attendanceDayEntity.getDescription()
        );
    }
}
