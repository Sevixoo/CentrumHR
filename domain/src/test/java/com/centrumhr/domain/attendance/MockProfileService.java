package com.centrumhr.domain.attendance;

import java.util.UUID;

/**
 * Created by Seweryn on 28.05.2017.
 */
public class MockProfileService implements  IProfileService {

    @Override
    public boolean isPregnant(UUID employeeUniqueId) {
        return false;
    }

    @Override
    public int getWorkTime(UUID employeeUniqueId) {
        return WORK_TIME_FULL;
    }
}
