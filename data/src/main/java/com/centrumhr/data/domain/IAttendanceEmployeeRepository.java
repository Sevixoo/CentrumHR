package com.centrumhr.data.domain;

import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.attendance.AttendanceEmployee;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 13.11.2016.
 */
public interface IAttendanceEmployeeRepository {

    void save(AttendanceEmployee department)throws DatabaseException;

    AttendanceEmployee load(UUID uniqueId)throws DatabaseException;

    List<AttendanceEmployee> list()throws DatabaseException;

    void delete(UUID uniqueId)throws DatabaseException;

}
