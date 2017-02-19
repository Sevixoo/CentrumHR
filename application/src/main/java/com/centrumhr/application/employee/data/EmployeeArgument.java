package com.centrumhr.application.employee.data;

import com.centrumhr.application.employee.dto.PersonalDataDTO;
import com.centrumhr.dto.employment.DepartmentDTO;
import com.centrumhr.dto.employment.WorkFunctionDTO;
import com.centrumhr.dto.employment.WorkTime;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 11.02.2017.
 */
public class EmployeeArgument {

    public UUID editingUniqueId;
    public PersonalDataDTO personalDataDTO;
    public List<DepartmentDTO> departments;
    public WorkFunctionDTO workFunction;
    public Date employmentDate;
    public boolean  isJudgment;
    public WorkTime workTime;

}
