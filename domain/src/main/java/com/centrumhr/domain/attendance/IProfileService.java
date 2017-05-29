package com.centrumhr.domain.attendance;

import java.util.UUID;

/**
 * Created by Seweryn on 28.05.2017.
 */
public interface IProfileService {
    
    int WORK_TIME_FULL = 1;

    boolean isPregnant(UUID employeeUniqueId);

    int getWorkTime(UUID employeeUniqueId);
}
