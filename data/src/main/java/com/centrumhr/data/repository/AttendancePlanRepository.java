package com.centrumhr.data.repository;

import com.centrumhr.data.domain.IAttendancePlanRepository;
import com.centrumhr.data.model.attendance.AttendancePlan;
import com.centrumhr.data.orm.Repository;
import com.centrumhr.data.orm.UnitOfWork;

/**
 * Created by Seweryn on 13.11.2016.
 */
public class AttendancePlanRepository extends Repository<AttendancePlan> implements IAttendancePlanRepository {

    public AttendancePlanRepository( UnitOfWork mUnitOfWork) {
        super(AttendancePlan.class, mUnitOfWork);
    }

}
