package com.centrumhr.data.repository;

import com.centrumhr.data.domain.IAttendanceEmployeeRepository;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.attendance.AttendanceEmployee;
import com.centrumhr.data.orm.Repository;
import com.centrumhr.data.orm.UnitOfWork;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 13.11.2016.
 */
public class AttendanceEmployeeRepository extends Repository<AttendanceEmployee> implements IAttendanceEmployeeRepository {

    public AttendanceEmployeeRepository( UnitOfWork mUnitOfWork) {
        super(AttendanceEmployee.class, mUnitOfWork);
    }
}
