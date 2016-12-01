package com.centrumhr.data.domain;

import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.model.employment.EmployeeDepartment;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 13.11.2016.
 */
public interface IAttendancePlanRepository  {

    void save(AttendancePlan department)throws DatabaseException;

    AttendancePlan load(UUID uniqueId)throws DatabaseException;

    List<AttendancePlan> list()throws DatabaseException;

    void delete(UUID uniqueId)throws DatabaseException;

}
