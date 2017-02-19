package com.centrumhr.application.shedule.data;

import com.centrumhr.dto.employment.EmployeeDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 11.02.2017.
 */
public class CreateAttendancePlanArgument {

    public String name;
    public Date month;
    public List<UUID> employees;

}
