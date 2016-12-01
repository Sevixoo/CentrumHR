package com.centrumhr.application.application.shedule.usecase;

import com.centrumhr.application.application.common.IExecutor;
import com.centrumhr.application.application.common.IHandler;
import com.centrumhr.application.application.common.UseCase;
import com.centrumhr.data.importer.AttendancePlanImporter;
import com.centrumhr.data.model.attendance.AttendancePlan;

/**
 * Created by Seweryn on 13.11.2016.
 */
public class SaveAttendancePlanUseCase extends UseCase<AttendancePlan> {

    private AttendancePlan mAttendancePlan;
    private AttendancePlanImporter mAttendancePlanImporter;

    public SaveAttendancePlanUseCase(IExecutor executor, IHandler handler, AttendancePlan mAttendancePlan, AttendancePlanImporter mAttendancePlanImporter) {
        super(executor, handler);
        this.mAttendancePlan = mAttendancePlan;
        this.mAttendancePlanImporter = mAttendancePlanImporter;
    }

    @Override
    public AttendancePlan execute() throws Exception {
        mAttendancePlanImporter.importData(mAttendancePlan);
        return mAttendancePlan;
    }
}
