package com.centrumhr.data.repository;

import com.centrumhr.data.domain.IAttendanceDayRepository;
import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.attendance.AttendanceDay;
import com.centrumhr.data.orm.Repository;
import com.centrumhr.data.orm.UnitOfWork;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 13.11.2016.
 */
public class AttendanceDayRepository extends Repository<AttendanceDay> implements IAttendanceDayRepository{

    public AttendanceDayRepository( UnitOfWork mUnitOfWork) {
        super(AttendanceDay.class, mUnitOfWork);
    }
}
