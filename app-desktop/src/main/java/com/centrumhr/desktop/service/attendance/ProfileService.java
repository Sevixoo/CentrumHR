package com.centrumhr.desktop.service.attendance;

import com.centrumhr.domain.attendance.IProfileService;

import java.util.UUID;

/**
 * Created by Seweryn on 28.05.2017.
 */
public class ProfileService implements IProfileService{
    @Override
    public boolean isPregnant(UUID employeeUniqueId) {
        return false;
    }

    @Override
    public int getWorkTime(UUID employeeUniqueId) {
        return WORK_TIME_FULL;
    }
}
